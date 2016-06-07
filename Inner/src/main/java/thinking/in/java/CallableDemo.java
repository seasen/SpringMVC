package thinking.in.java;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by seasen on 2016/1/25.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> arrayList = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++)
            arrayList.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs:arrayList) {
            try {
                System.out.println(fs.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
