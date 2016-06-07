package crawl.weibo.sina.parse.parser;

import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.oldbean.newStars;
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
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seasen on 2016/3/8.
 */
public class FanAccountInfor {
    public static final Logger log = Logger.getLogger(StarID.class);
    public static int urlNum = 1;

    public static void main(String[] args) throws IOException {
        LoginWeibo lw = new LoginWeibo();
        lw.loginAndGetConnection("953862461@qq.com","shaosen131");
        FanAccountInfor fa = new FanAccountInfor();
        fa.getFanAccountInfor();
    }
    public List<Stars> getFanAccountInfor() throws IOException {
        List<Stars> result = new ArrayList<Stars>();
        String content = null;
        Document contentDoc = null;
        List<Stars> namelist = GetInfor.getStarName();
        Iterator<Stars> it = namelist.iterator();
        reConnection:while (it.hasNext()) {
            Stars stars = new Stars();
            String url = "http://m.weibo.cn/main/pages/index?containerid=100103type%3D3%26q%3D";
            String url2 = "&type=user&queryVal=";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = it.next().getName();
            url = url + name + url2 + name;
            System.out.println((urlNum++)+"、 "+url);
            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
            HttpConnectionParams.setSoTimeout(params, 10 * 1000);
            AbstractHttpClient httpClient = new DefaultHttpClient(params);
            HttpGet getHttp = new HttpGet(url);
            // 设置HTTP Header
            getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
            HttpResponse response = null;
            // 获得信息载体
            try {
                response = httpClient.execute(getHttp);
            } catch (Exception e) {
                System.err.println("访问出错");
                if (response == null) {
                    System.err.println("什么鬼？");
                }
                log.info(">> Error: 访问失败 - 明星名字："+name+"，");
                continue reConnection;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 转化为文本信息, 设置爬取网页的字符集，防止乱码
                content = EntityUtils.toString(entity, "UTF-8");
            }
            contentDoc = getPageDocument(content);
            int fans = checkFunNumber(name,contentDoc);
            stars.setName(name);
            stars.setTieNum(fans);
            result.add(stars);
        }
        System.out.println("访问成功！");
        return result;
    }
    public Document getPageDocument(String content){
        return Jsoup.parse(content);
    }
    public int checkFunNumber(String name,Document doc){
        int i = 3, fans = 0;
        String user = "";
        String fansNumber = "";
        String regexUser = "screen_name\":\"(.*?)\",\"profile_image_url";
        String regexFansNum = "fansNum\":\"(.*?)\",\"follow_me";
        Pattern patternUser = Pattern.compile(regexUser);
        Pattern patternFanNum = Pattern.compile(regexFansNum);
        Matcher matcherUser = patternUser.matcher(doc.html());
        Matcher matcherFanNum = patternFanNum.matcher(doc.html());
        while(matcherUser.find()&&i-->0&&matcherFanNum.find()){
            user = publicMethod.decodeUnicode(matcherUser.group(1));
            fansNumber = publicMethod.decodeUnicode(matcherFanNum.group(1));
            fans += checkUser(name,user,fansNumber);
            NativeFile(user,fansNumber);
            System.out.println(user+"-----"+fansNumber);
        }
        NativeFile("**************","**************");
        return fans;
    }
    public int checkUser(String name,String user,String fansNumber){
        boolean result1 = user.contains("贴吧")||user.contains("官微")||user.contains("后援团")||user.contains("微博")||user.contains("影工厂")||user.contains("微吧")||user.contains("歌迷")||user.contains("粉丝会");
        boolean result2 = user.contains("貼吧")||user.contains("後援團")||user.contains("微博")||user.contains("影工廠")||user.contains("粉絲會");
        if(result1||result2){
            if(fansNumber.contains("万")){
                String[] str = fansNumber.split("万");
                return 10000*Integer.parseInt(str[0]);
            }else{
                    return Integer.parseInt(fansNumber);
            }
        }
        System.out.println(user+"-------"+fansNumber);
        if(fansNumber.contains("万")){
            String[] str = fansNumber.split("万");
            if (Integer.parseInt(str[0])>70)
                return 10000*Integer.parseInt(str[0]);
        }else{
            return Integer.parseInt(fansNumber);
        }

        return 0;
    }

    public boolean NativeFile(String user, String FansNumber) {
        File fileWrite= new File("E:\\FilmData\\star\\star.txt");
        if (!fileWrite.exists()) {
            try {
                fileWrite.getParentFile().mkdirs();//构建文件夹
                fileWrite.createNewFile();//构建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            FileWriter fw = null;
            try {
                fw = new FileWriter(fileWrite, true);
            } catch (IOException e) {
                return true;
            }
            PrintWriter pw = new PrintWriter(fw);
            pw.println(user+"----"+FansNumber);
            pw.flush();
            try {
                fw.flush();
                pw.close();
                fw.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        return true;
    }
}
