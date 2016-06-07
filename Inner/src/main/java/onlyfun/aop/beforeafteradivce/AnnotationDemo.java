package onlyfun.aop.beforeafteradivce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/16.
 */
public class AnnotationDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        IHello hs = (IHello)context.getBean("helloSpeaker");
        hs.hello("ss");
    }
}
