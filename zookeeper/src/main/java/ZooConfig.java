/**
 * Created by ankit.chaudhury on 09/08/17.
 */
public class ZooConfig {
    private String zooKeeperConnection;
    private String ZooKeeperLeaderPath;

    public String getZooKeeperConnection() {
        return zooKeeperConnection;
    }

    public void setZooKeeperConnection(String zooKeeperConnection) {
        this.zooKeeperConnection = zooKeeperConnection;
    }

    public String getZooKeeperLeaderPath() {
        return ZooKeeperLeaderPath;
    }

    public void setZooKeeperLeaderPath(String zooKeeperLeaderPath) {
        ZooKeeperLeaderPath = zooKeeperLeaderPath;
    }
}
