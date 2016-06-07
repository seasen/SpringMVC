package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/8.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        obj2.getMessage();
    }
}
