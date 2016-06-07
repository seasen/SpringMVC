package crawl.weibo.sina.parse.parser;

import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.utils.Contants;
import crawl.weibo.sina.parse.utils.publicMethod;
import crawl.weibo.sina.parse.worker.GetInfor;
import crawl.weibo.sina.parse.worker.LoginWeibo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seasen on 2016/3/9.
 */
public class Profile {

    public static void main(String[] args) {
        Profile p = new Profile();
        p.getProfiles();
    }
    static GetInfor gi = new GetInfor();
    public static int i = 1;
    /**
     * 获取明星基本信息，返回List
     * */
    public List<Stars> getProfiles(){
        Connection conn = GetInfor.getConn("sina_weibo","root","root");
        LoginWeibo lw = new LoginWeibo();
        lw.loginAndGetConnection("953862461@qq.com","shaosen131");
        List<Stars> result = new ArrayList<Stars>();
        List<Stars> list = gi.selectStars();
        Iterator<Stars> it = list.iterator();
        Stars sts = new Stars();
        while(it.hasNext()){
            int random = (int)(1000*Math.random());
            random = random+700;
            if(random>1500)
                random = random - 400;
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sts = getElement(it.next().getWeiId());
            InsertStars(conn,sts);

        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean InsertStars(Connection conn, Stars starss){

        Statement st = null;
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
        }
        return true;
    }
    /**
     * 解析
     */
    public Stars getElement(String weiid){
        Stars stars = new Stars();
        Document doc = crwalInformation(weiid);
        String fansNum = "";
        int i = 10;
        while (fansNum==""&&i-->0)
        {
            fansNum = publicMethod.patternMethod(Contants.regexFansNum,doc);
            if(i==1)
            {
                stars.setFansNum(0);
                stars.setFollow(0);
                stars.setWeiboNum(0);
                stars.setNickName("");
                stars.setBriefIntro("");
                stars.setWeiId(weiid);
                return stars;
            }
        }
        fansNum = publicMethod.patternMethod(Contants.regexFansNum,doc);
        String follow = publicMethod.patternMethod(Contants.regexFollow,doc);
        String weiboNum = publicMethod.patternMethod(Contants.regexWeiboNum,doc);
        String nickName = publicMethod.decodeUnicode(publicMethod.patternMethod(Contants.regexNickName,doc));
        String briefIntro = publicMethod.decodeUnicode(publicMethod.patternMethod(Contants.regexBriefIntro,doc));

        stars.setFansNum(Integer.parseInt(fansNum));
        stars.setFollow(Integer.parseInt(follow));
        stars.setWeiboNum(Integer.parseInt(weiboNum));
        stars.setNickName(nickName);
        stars.setBriefIntro(briefIntro);
        stars.setWeiId(weiid);
        System.out.println("昵称："+nickName+"，粉丝:"+fansNum+",关注："+follow+",微博数："+weiboNum+",简介："+briefIntro);
        return stars;
    }
    public  Document crwalInformation(String weiid){
        String url = "http://m.weibo.cn/u/"+weiid;
        System.out.println((i++)+"、"+url);
        String content = null;
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        AbstractHttpClient httpClient = new DefaultHttpClient(params);
        HttpGet getHttp = new HttpGet(url);
        // 设置HTTP Header
        getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
        HttpResponse response = null;
        // 获得信息载体
        //访问如果出错三次以上则返回空
        boolean temp = true;
        for (int j = 0; j < 3; j++) {
            try {
                response = httpClient.execute(getHttp);
                if(temp == true){
                    temp = true;
                    break;}
            } catch (Exception e) {
                System.err.println("访问出错");
                temp = false;
            }
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 转化为文本信息, 设置爬取网页的字符集，防止乱码
            try {
                content = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Document doc = Jsoup.parse(content);

        return doc;
    }
    public boolean NativeFile(String weiId, String nickName,int fansNum,int follow,String briefIntro) {
        File fileWrite= new File("E:\\FilmData\\star\\stars.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileWrite, true);
        } catch (IOException e) {
            return true;
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("微博ID："+weiId+",微博昵称："+nickName+",粉丝数量："+fansNum+",关注数："+follow+",简介："+briefIntro);
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
