/**
 * Created by ankit.chaudhury on 09/08/17.
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderElector {
    private final Logger logger = LoggerFactory.getLogger(LeaderElector.class);

    private  String latchPath;
    private CuratorFramework client;
    private LeaderLatch leaderLatch;
    private boolean started = false;
    private static LeaderElector leaderElector;

    private LeaderElector() throws Exception {

    }

    public static LeaderElector getInstance() throws Exception {
        if(leaderElector == null) {
            synchronized (LeaderElector.class) {
                if(leaderElector == null) {
                    leaderElector = new LeaderElector();
                }
            }
        }
        return leaderElector;
    }

    public void start(final LeaderCallback callback) throws Exception {
        if(started == false) {
            synchronized (leaderElector) {
                if (started == false) {
                    logger.info("Starting leader elector");
                    started = true;
                    ZooConfig zooConfig = ZooConfigCreator.getZooConfig();
                    this.client = CuratorFrameworkFactory.newClient(zooConfig.getZooKeeperConnection(), new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
                    this.latchPath = zooConfig.getZooKeeperLeaderPath();

                    client.start();
                    client.getZookeeperClient().blockUntilConnectedOrTimedOut();
                    leaderLatch = new LeaderLatch(client, latchPath);
                    leaderLatch.start();

                    leaderLatch.addListener(new LeaderLatchListener() {
                        @Override
                        public void isLeader() {
                            try {
                                callback.run();
                            } catch (Exception e) {
                                logger.error("Error in leader : ", e);
                            }
                        }

                        @Override
                        public void notLeader() {
                        }
                    });
                }
            }
        }
    }

    public boolean isLeader() {
        if(!started)
            throw new IllegalArgumentException("leader not started yet, please call leader.start method first");
        return leaderElector.isLeader();
    }
}
