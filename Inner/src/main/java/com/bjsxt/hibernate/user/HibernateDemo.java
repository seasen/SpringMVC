package com.bjsxt.hibernate.user;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by seasen on 2016/3/15.
 */
public class HibernateDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        IUserDAO userDAO = new UserDAO(sessionFactory);
        User user = new User();
        user.setName("heiheihei");
        user.setAge(new Integer(25));
        userDAO.insert(user);
        user = userDAO.find(new Integer(1));
        System.out.println("name:"+user.getName());
        }
}
