/**
 * Created by ankit.chaudhury on 09/08/17.
 */
public class ZooConfigCreator {
    private static final String ZOO_CONNECTION = "zookeeper.connection";
    private static final String ZOO_LEADER_PATH = "zookeeper.leaderPath";

    public static ZooConfig getZooConfig() {
        ZooConfig zooConfig = new ZooConfig();

        zooConfig.setZooKeeperConnection("10.85.250.123:2181,10.85.226.224:2181,10.85.226.225:2181");
        zooConfig.setZooKeeperLeaderPath("/fsd");
        return zooConfig;
    }
}
