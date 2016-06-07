package onlyfun.aop.beforeafteradivce;

/**
 * Created by seasen on 2016/1/11.
 */
public class HelloSpeaker implements IHello {
    public void hello(String name) {
        System.out.println("hello,"+name);
    }
}
