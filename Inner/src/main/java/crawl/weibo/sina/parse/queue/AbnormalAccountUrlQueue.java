package crawl.weibo.sina.parse.queue;

/**
 * 已访问账号队列
 * Created by seasen on 2016/3/5.
 */
import java.util.HashSet;
public class AbnormalAccountUrlQueue {
    public static HashSet<String> visitedUrlQueue = new HashSet<String>();

    public synchronized static void addElement(String url){
        visitedUrlQueue.add(url);
    }

    public synchronized static boolean isContains(String url){
        return visitedUrlQueue.contains(url);
    }

    public synchronized static int size(){
        return visitedUrlQueue.size();
    }
}
