package spring.mybatis;

/**
 * Created by seasen on 2016/5/26.
 */
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader    = Resources.getResourceAsReader("conf.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = (User) session.selectOne("com.yihaomen.mybatis.models.UserMapper.selectUserByID", 1);
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }
    }
}