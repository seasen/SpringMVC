package onlyfun.lookupmethod;

/**
 * Created by seasen on 2016/1/10.
 */
public abstract class MessageManager {
    final static int i = 1;
    public void play(){
        Message message = createMessage();
        System.out.println(message);
    }
    protected abstract Message createMessage();
}
