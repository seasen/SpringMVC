package onlyfun.lookupmethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;

/**
 * Created by seasen on 2016/1/10.
 *  <bean id="sysMessage" class="onlyfun.lookupmethod.Message" scope="prototype"></bean>
 <bean id="messageManager" class="onlyfun.lookupmethod.MessageManager">
 <lookup-method name="createMessage" bean="sysMessage"/>
 </bean>
 *
 */
public class LookDemo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        MessageManager mm = (MessageManager)context.getBean("messageManager");
        try {
            mm.play();
            Thread.sleep(1000);
            mm.play();
            Thread.sleep(1000);
            mm.play();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
