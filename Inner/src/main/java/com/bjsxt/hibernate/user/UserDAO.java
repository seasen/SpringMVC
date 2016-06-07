package com.bjsxt.hibernate.user;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO implements IUserDAO {
    private SessionFactory sessionFactory;
    public UserDAO(){}
    public UserDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public  void insert(User user){
        //取得Session
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //直接储存对象
        session.save(user);
        tx.commit();
        session.close();
    }
    public User find(Integer id){
        Session session = sessionFactory.openSession();
        User user = (User)session.get(User.class,id);
        Hibernate.initialize(user);
        session.close();
        return user;
    }
}
