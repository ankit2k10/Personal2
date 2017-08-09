/**
 * Created by ankit.chaudhury on 09/08/17.
 */
public class Test {
    public static void main(String[] str) throws Exception {
        MyLeaderCallBack callBack = new Test().new MyLeaderCallBack();
        LeaderElector leaderElector = LeaderElector.getInstance();
        System.out.println("Starting leader elector");
        leaderElector.start(callBack);

        int n=1;
        while(true) {
            System.out.println("I m busy waiting " + n++);
            Thread.sleep(5000);
        }
    }

    class MyLeaderCallBack implements LeaderCallback {
        public void run() throws InterruptedException {
            System.out.println("I am the leader : " + Thread.currentThread().getName());
        }

        @Override
        public void stop() throws InterruptedException {
            System.out.println("I am not the leader : " + Thread.currentThread().getName());
        }
    }
}
