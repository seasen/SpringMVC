package crawl.weibo.sina.parse.worker;
import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.oldbean.newStars;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.*;
/**
 * Created by seasen on 2016/3/6.
 */
public class GetInfor {
    public static void main(String[] args) {
        GetInfor.getStarName();
    }
    /**
     * 获得电影明星的名字
     * */
    public static List<Stars> getStarName(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        Statement st = null;
        ResultSet rs = null;
        List<Stars> namelist = new ArrayList<Stars>();
        try {
             st = conn.createStatement();
            rs = st.executeQuery("select star_name from stars WHERE id>7326");
            while(rs.next()){
                Stars ns = new Stars();
                ns.setName(rs.getString("star_name"));
                namelist.add(ns);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                GetInfor.close(conn,st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return namelist;
    }
    public List<Stars> selectStars(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        Statement st = null;
        ResultSet rs = null;
        List<Stars> list = new ArrayList<Stars>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select weiId,weiboNum from stars WHERE id>=12636 AND weiId IS NOT NULL");
            while(rs.next()){
                Stars ns = new Stars();
                ns.setWeiId(rs.getString("weiId"));
                ns.setWeiboNum(rs.getInt("weiboNum"));
                list.add(ns);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                GetInfor.close(conn,st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<Stars> selectStarsTest(String name){
        name =  name.substring(0, 1).toUpperCase() + name.substring(1);
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        Statement st = null;
        ResultSet rs = null;

        List<Stars> list = new ArrayList<Stars>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select "+name+" from stars");
            while(rs.next()){
                Stars ns = new Stars();
                Class clazz = (Class) ns.getClass();
                Method method = clazz.getMethod("set"+name,String.class);
                method.invoke(ns,rs.getString(name));
                list.add(ns);
        }
    } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                GetInfor.close(conn,st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static Connection getConn(String db,String user,String name) {
        String url="jdbc:mysql://localhost:3306/"+db+"?user="+user+"&password="+name+"&useUnicode=true&characterEncoding=GBK";
        // TODO Auto-generated method stub
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn,Statement st) throws Exception{
        if(st!=null){
            st.close();
            if(conn!=null)
                conn.close();}
    }
}