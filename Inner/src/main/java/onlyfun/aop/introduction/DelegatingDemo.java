package onlyfun.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/16.
 */
public class DelegatingDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        ISome some = context.getBean("proxyFactoryBean",ISome.class);
        some.setSome("shaosen");
        System.out.println(some.getSome());
        try{
            ((ILockable)some).lock();
            some.setSome("邵森");
            System.out.println(some.getSome());
        }catch (Throwable e){
            e.printStackTrace();
        }
        ((ILockable)some).unlock();
        some.setSome("邵森");
        System.out.println(some.getSome());
    }
}
