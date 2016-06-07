package thinking.in.java;

/**
 * Created by seasen on 2016/1/25.
 */
public class LiftOff {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public String status(){
        return "#"+id+"("+(countDown>0?countDown:"LiftOff!")+",)";
    }

    public static void main(String[] args) {
    }
}
