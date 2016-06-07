package spring.datasource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by seasen on 2016/1/19.
 */
public class SpringDAODemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/applicationContext.xml");
        User user = new User();
        user.setName("what");
        user.setAge(new Integer(22));
        IUserDAO userDAO = context.getBean("userDAO",IUserDAO.class);
        userDAO.insert(user);
        user = userDAO.find(new Integer(1));
        System.out.println("name:"+user.getName());
    }
}
