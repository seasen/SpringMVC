package onlyfun.beanprocess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/10.
 */
public class BeanProcessDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        HelloBeanPost hbp = context.getBean("helloBeanPost",HelloBeanPost.class);
        System.out.println(hbp.getHelloWorld()+" "+hbp.getName());
    }
}
