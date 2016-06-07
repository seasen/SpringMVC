package spring.datasource;

/**
 * Created by seasen on 2016/1/17.
 */
public interface IUserDAO {
    public void insert(User user);
    public User find(Integer id);
}
