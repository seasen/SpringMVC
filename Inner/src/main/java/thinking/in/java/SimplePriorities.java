package thinking.in.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by seasen on 2016/1/25.
 */
public class SimplePriorities implements Runnable {
    private int countDown=5;
    private volatile double d;
    private int priority;
    public SimplePriorities(int priority){
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread()+":"+countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while(true){
            for (int i = 1; i < 10; i++) {
                d += (Math.PI + Math.E)/(double)i;
                if(i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown==0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
