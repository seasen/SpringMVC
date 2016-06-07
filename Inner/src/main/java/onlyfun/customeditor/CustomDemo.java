package onlyfun.customeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/11.
 */
public class CustomDemo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        HelloBeanCustom hbc = context.getBean("helloBeanCustom",HelloBeanCustom.class);
        System.out.println(hbc.getUser().getName()+" "+hbc.getUser().getNumber());
    }

}
