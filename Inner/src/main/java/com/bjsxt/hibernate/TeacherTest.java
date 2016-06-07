package com.bjsxt.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import java.util.*;
/**
 * Created by seasen on 2016/1/24.
 */
public class TeacherTest {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";//select name from Wife w where  w.id = 1
        Query query = session.createQuery(sql);
        List list=query.list();
        String test1=(String)list.get(0);
        System.out.println(test1);
       /* Wife f = new Wife();
        f.setName("leib");s
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();
        session1.save(f);
        session1.getTransaction().commit();*/
        /*Wife f = new Wife();
        f.getId();
        Husband t = new Husband();
        t.setName("t2");
        t.setWife(f);
        //t.setBirthDate(new Date());
        //Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.getCurrentSession();
        session2.beginTransaction();
        session2.save(t);
        session2.getTransaction().commit();*/
   /*     Session session2 = sessionFactory.getCurrentSession();
        System.out.println(session == session2);
        session.getTransaction().commit();
        Session session3 = sessionFactory.getCurrentSession();
        System.out.println(session == session3);*/
        sessionFactory.close();
    }
}
