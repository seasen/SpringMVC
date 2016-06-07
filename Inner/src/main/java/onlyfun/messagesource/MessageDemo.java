package onlyfun.messagesource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by seasen on 2016/1/11.
 */
public class MessageDemo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        Object[] arguments = new Object[]{"逗比", Calendar.getInstance().getTime()};
        System.out.println(context.getMessage("userLogin",arguments, Locale.US));
        System.out.println(context.getMessage("userLogin",arguments, Locale.TAIWAN));
    }
}
