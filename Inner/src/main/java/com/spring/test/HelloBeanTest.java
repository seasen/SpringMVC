package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/9.
 * <bean id="date" class="java.util.Date"/>
 <bean id="helloBean" class="com.spring.test.HelloBean" scope="prototype">
 <property name="date" ref="date"/>
 <property name="helloworld"><null/></property>
 </bean>
 *
 */
public class HelloBeanTest {
    public static void main(String[] args) {
       /* ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        HelloBean hb = context.getBean("helloBean",HelloBean.class);
        HelloBean hb2 = (HelloBean) context.getBean("helloBean");
        hb2.setHelloworld("wokaka");
        System.out.println(hb.getDate()+":"+hb2.getDate());
        System.out.println(hb.getHelloworld()+":"+hb2.getHelloworld());*/
    }
}
