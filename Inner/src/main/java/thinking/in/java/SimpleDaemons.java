package thinking.in.java;


/**
 * Created by seasen on 2016/1/25.
 */
public class SimpleDaemons implements Runnable {
    public void run() {
        try{
            while(true){
                Thread.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SimpleDaemons());
            t.setDaemon(true);
            t.start();
        }
        System.out.println("sleep");
        Thread.sleep(1000);
    }
}
