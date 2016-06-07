package com.bjsxt.hibernate.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/3/15.
 */
public class SpringHibernateDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        IUserDAO userDAO = (IUserDAO) context.getBean("userDAO");
        User user = new User();
        user.setName("fuck");
        user.setAge(new Integer(20));
        userDAO.insert(user);
        user = userDAO.find(new Integer(1));
        System.out.println("name:"+user.getName());
    }
}
