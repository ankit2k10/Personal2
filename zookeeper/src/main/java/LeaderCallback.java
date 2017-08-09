/**
 * Created by ankit.chaudhury on 09/08/17.
 */
public interface LeaderCallback {
    public void run() throws InterruptedException;

    public void stop() throws InterruptedException;
}
