package crawl.weibo.sina.parse.queue;

/**
 * Created by seasen on 2016/3/5.
 */
import java.util.HashSet;

/**
 * 已访问url队列
 * @author yuki
 *
 */
public class VisitedWeiboUrlQueue {
    public static HashSet<String> visitedUrlQueue = new HashSet<String>();
    public static int count = 0;

    public synchronized static void addElement(String url){
        // visitedUrlQueue.add(url);
        count++;
    }

    public synchronized static boolean isContains(String url){
        return visitedUrlQueue.contains(url);
    }

    public synchronized static int size(){
        // return visitedUrlQueue.size();
        return count;
    }
}
