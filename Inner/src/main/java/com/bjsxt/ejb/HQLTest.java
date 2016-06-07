package com.bjsxt.ejb;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by seasen on 2016/1/24.
 */
public class HQLTest {
    private static SessionFactory sf;
    static {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    public static void main(String[] args) {
        HQLTest t = new HQLTest();
        //t.Insert();
        t.testHQL_13();
    }
    public void testHQL_17() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m.id between 3 and 5");

        for(Object o : q.list()) {
            Object[] m = (Object[])o;
            System.out.println(m[0] + "-" + m[1]+"-"+m[2]);
        }
        session.getTransaction().commit();
        session.close();
    }
    public void testHQL_13() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t join t.category c "); //join Category c
        for(Object o : q.list()) {
            Object[] m = (Object[])o;
            System.out.println(m[0] + "-" + m[1]);
        }
        session.getTransaction().commit();
        session.close();

    }

    public void testHQL_07() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category c order by c.name desc");
        q.setMaxResults(5);
        q.setFirstResult(0);
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getId() + "-" + c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    public void testHQL_09() {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.category.id = 1");
        List<Topic> topics = (List<Topic>)q.list();
        for(Topic t : topics) {

            System.out.println(t.getCategory().getId());
        }
        session.getTransaction().commit();
        session.close();

    }
    public void Insert(){
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        for(int i=0; i<10; i++) {
            Category c = new Category();
            c.setName("c" + i);
            session.save(c);
        }
        for(int i=0; i<10; i++) {
            Category c = new Category();
            c.setId(1);
            Topic t = new Topic();
            t.setCategory(c);
            t.setTitle("t" + i);
            t.setCreateDate(new Date());
            session.save(t);
        }
        for(int i=0; i<10; i++) {
            Topic t = new Topic();
            t.setId(1);
            Msg m = new Msg();
            m.setCont("m" + i);
            m.setTopic(t);
            session.save(m);
        }
        session.getTransaction().commit();
        session.close();
        sf.close();
    }
}
