package onlyfun.aop.namematch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/11.
 */
public class SpringDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        IHello helloProxy = (IHello)context.getBean("helloProxy2");
        helloProxy.helloNewbie("邵森");
        helloProxy.helloMaster("nani");
    }
}
