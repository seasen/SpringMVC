package crawl.weibo.sina.parse.parser;

import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.bean.Weibo;
import crawl.weibo.sina.parse.worker.GetInfor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by seasen on 2016/3/7.
 */
public class InsertMySQL {
    static InsertMySQL insert = new InsertMySQL();
    StarID starID = new StarID();
    Stars star = new Stars();
    FanAccountInfor fa = new FanAccountInfor();
    public static void main(String[] args) {
        insert.InsertStars();
    }
    /**
     * 把明星基本信息导入到数据库
     * */
    public boolean InsertStars(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        Stars starss = new Stars();
        Profile profile = new Profile();
        List<Stars> stars = profile.getProfiles();
        Statement st = null;
        Iterator<Stars> it = stars.iterator();
        reconn:while(it.hasNext()){
            starss = it.next();
            System.out.println(star.getWeiId()+","+star.getNickName());
            String sql = "update stars set fansNum = "+starss.getFansNum()+",weiboNum= "+starss.getWeiboNum()+", nickName = "+"'"+starss.getNickName()+
                    "',follow = "+starss.getFollow()+",briefintro = "+"'"+starss.getBriefIntro()+"' WHERE weiId = '"+starss.getWeiId()+"'";
            try {
                st = conn.createStatement();
                int r = st.executeUpdate(sql);
                if(r>0)
                    System.out.println("插入成功！");
                else
                    System.out.println("插入失败！");
            } catch (SQLException e) {
                System.err.println("导入失败，保存文件到本地");
                profile.NativeFile(star.getWeiId(),star.getNickName(),star.getFansNum(),star.getFollow(),star.getBriefIntro());
                continue reconn;
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void InsertWeiboTieFans(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        List<Stars> tie = new ArrayList<Stars>();
        try {
            tie = fa.getFanAccountInfor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Stars> it = tie.iterator();
        PreparedStatement ps = null;
        reConn:while(it.hasNext()){
            star = it.next();
            String sql = "UPDATE stars SET tieNum= "+star.getTieNum()+" WHERE star_name="+star.getName();
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt((1),star.getTieNum());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("插入失败:"+star.getName()+","+star.getTieNum());
                continue reConn;
            }
        }
        try {
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入数据结束：");
    }/**
     *插入微博ID号和名字
     */
    public void InsertWeiboId(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        List<Stars> starid = new ArrayList<Stars>();
        try {
            starid = starID.starNameId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Stars> it = starid.iterator();
        Statement ps = null;
        while(it.hasNext()){
            star = it.next();
            String sql = "UPDATE stars SET weiId='"+star.getWeiId()+"' WHERE star_name='"+star.getName()+"'";
            try {
                ps = conn.createStatement();
                ps.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if(ps != null)
                ps.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("插入数据成功：");
    }
    /**
    * 把微博所有的数据导入到weibo数据里面
    * */
    public synchronized static void insertAllWeiBo(List<Weibo> weibos){
        Weibo wb = null;
        Connection conn = GetInfor.getConn("sina_weibo","root","root");

        Iterator<Weibo> it = weibos.iterator();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into weibo(WeiId,content,postTime,hasPic,isRepost,recontext,repostNum,comment,laud,weiboTH) value(?,?,?,?,?,?,?,?,?,?)";
        lianjie:while(it.hasNext()){
            wb = it.next();
            try {
                ps = conn.prepareStatement(sql);
                ps.setString((1),wb.getWeiId());
                ps.setString((2),wb.getContent());
                ps.setString((3),wb.getPostTime());
                ps.setBoolean((4),wb.getHasPic());
                ps.setBoolean((5),wb.getIsRepost());
                ps.setString((6),wb.getRecontext());
                ps.setInt((7),wb.getRepostNum());
                ps.setInt((8),wb.getComment());
                ps.setInt((9),wb.getLaud());
                ps.setInt((10),wb.getWeiboTH());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if(ps!=null)
                ps.close();
            conn.commit();
            if(conn!=null)
                conn.close();
            System.out.println("插入数据成功");
        } catch (SQLException e) {
            System.out.println("插入数据失败，导入本地文件");
        }
    }
    public static boolean NativeFile(Weibo wb) {
        File fileWrite= new File("E:\\FilmData\\star\\weibo.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileWrite, true);
        } catch (IOException e) {
            return true;
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(wb.getWeiId()+"\t"+wb.getContent()+"\t"+wb.getPostTime()+"\t"+wb.getHasPic()
                +"\t"+wb.getIsRepost()+"\t"+wb.getRecontext()+"\t"+wb.getRepostNum()+"\t"+wb.getComment()
                +"\t"+wb.getLaud()+"\t"+wb.getWeiboTH());
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        }catch(IOException e){
            System.err.println("写入失败");
            return false;
        }
        return true;
    }
}