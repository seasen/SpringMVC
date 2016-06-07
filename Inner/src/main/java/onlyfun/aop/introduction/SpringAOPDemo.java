package onlyfun.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/16.
 */
public class SpringAOPDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        ISome some = context.getBean("proxyFactoryBean",ISome.class);
        some.domSome();
        ((IOther)some).doOther();
    }
}
