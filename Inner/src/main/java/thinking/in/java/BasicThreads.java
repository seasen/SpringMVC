package thinking.in.java;

/**
 * Created by seasen on 2016/1/25.
 */
public class BasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++)
            new Thread(String.valueOf(new LiftOff())).start();
        System.out.println("主线程end!");
    }
}
