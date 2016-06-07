package spring.datasource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import  javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class UserDAO implements IUserDAO{
/*   private DataSourceTransactionManager transactionManager;
    private DefaultTransactionDefinition def;*/
    private JdbcTemplate jdbcTemplate;
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
/*        transactionManager =
                new DataSourceTransactionManager(dataSource);
        def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(
                TransactionDefinition.PROPAGATION_REQUIRED
        );*/
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(User user) {
        final String name = user.getName();
        final int age = user.getAge().intValue();
        jdbcTemplate.update("INSERT INTO user (name,age)"+"VALUES('" + name +"',"+ age +")");
        /*TransactionStatus status = transactionManager.getTransaction(def);
        try{
            //jdbcTemplate.update("INSER INTO user (name,age)"+"VALUES('" + name +"',"+ age +")");
        }catch (DataAccessException e){
            transactionManager.rollback(status);
            throw e ;
        }
        transactionManager.commit(status);*/
    }
    @Transactional(readOnly = true)
    public User find(Integer id) {
        List rows = jdbcTemplate.queryForList("SELECT * FROM user WHERE id="+id.intValue());
        Iterator it = rows.iterator();
        if(it.hasNext()){
            Map userMap = (Map)it.next();
            Integer i = new Integer(userMap.get("id").toString());
            String name = userMap.get("name").toString();
            Integer age = new Integer(userMap.get("age").toString());
            User user = new User();
            user.setId(i);
            user.setAge(age);
            user.setName(name);
            return user;
        }
        return null;
    }
    /* private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(User user) {
        String name = user.getName();
        int age = user.getAge().intValue();
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("INSERT INTO user (name,age) VALUES (?,?)");
            stmt.setString(1,name);
            stmt.setInt(2,age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(stmt != null)
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public User find(Integer id) {
       Connection conn = null;
        PreparedStatement stmt =null;
        try{
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM user WHERE id=?");
            stmt.setInt(1,id.intValue());
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                Integer in = new Integer(result.getInt(1));
                String name = result.getString(2);
                Integer age = new Integer(result.getInt(3));

                User user = new User();
                user.setId(in);
                user.setName(name);
                user.setAge(age);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(stmt != null)
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }*/
}
