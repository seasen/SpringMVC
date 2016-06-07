package onlyfun.aop.namematch;

/**
 * Created by seasen on 2016/1/11.
 */
public class HelloSpeaker implements IHello{
    public void helloNewbie(String name) {
        System.out.println("hello,"+name+"newbie!");
    }
    public void helloMaster(String name) {
        System.out.println("hello,"+name+" master!");
    }
}
