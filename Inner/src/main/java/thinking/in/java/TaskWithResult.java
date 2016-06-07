package thinking.in.java;

import java.util.concurrent.Callable;

/**
 * Created by seasen on 2016/1/25.
 */
public class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id ){
        this.id = id;
    }
    public String call(){
        return "result of TaskWithResult " + id;
    }
}
