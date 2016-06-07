package onlyfun.aop.beforeafteradivce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/11.
 */
public class SpringAOPDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        IHello helloProxy = (IHello)context.getBean("helloProxy");
        helloProxy.hello("邵森");
    }
}
