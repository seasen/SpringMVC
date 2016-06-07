package com.bjsxt.hibernate.user;

/**
 * Created by seasen on 2016/1/17.
 */
public interface IUserDAO {
    public void insert(User user);
    public com.bjsxt.hibernate.user.User find(Integer id);
}
