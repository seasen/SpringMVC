package onlyfun.caterpillar2;

import onlyfun.caterpillar.IMusicBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/9.
 * @name Spring
 * <bean id="inheritDemo" abstract="true">
 <property name="name"><value>shaosen</value></property>
 <property name="age"><value>232</value></property>
 </bean>
 <bean id="some" class="onlyfun.caterpillar2.SomeBean" parent="inheritDemo">
 <property name="age"><value>24</value></property>
 </bean>
 */

public class InheritDemo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        SomeBean somebean = context.getBean("some",SomeBean.class);
        System.out.println(somebean.getAge()+" "+somebean.getName());
    }
}

