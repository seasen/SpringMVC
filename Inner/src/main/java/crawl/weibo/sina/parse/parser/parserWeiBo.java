package crawl.weibo.sina.parse.parser;
import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.bean.Weibo;
import crawl.weibo.sina.parse.queue.WeiboUrlQueue;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by seasen on 2016/3/10.
 * */
public class parserWeiBo {
    List<String> urls = new ArrayList<String>();//保存访问失败的urls
    List<Weibo> weibos = new ArrayList<Weibo>();
    static GetInfor gi = new GetInfor();
    public static int weiboTH = 1;
   public void getWeiBo(){
        Stars star = new Stars();
        LoginWeibo lw = new LoginWeibo();
        lw.loginAndGetConnection("953862461@qq.com","shaosen131");
        List<Stars> list = gi.selectStars();
        Iterator<Stars> it = list.iterator();
        while(it.hasNext()){
            weiboTH = 1;
            star = it.next();
            int random = (int)(1000*Math.random());
            random = random+800;
            if(random>1500)
                random = random - 400;
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getAllWeiBo(star.getWeiId());
        }
    }
    /**
     * @content 微博ID队列
     * */
    public void getWeiBo2(){
        Stars star = new Stars();
        LoginWeibo lw = new LoginWeibo();
        lw.loginAndGetConnection("953862461@qq.com","shaosen131");
        List<Stars> list = gi.selectStars();
        Iterator<Stars> it = list.iterator();
        while(it.hasNext()){
            weiboTH = 1;
            star = it.next();
            WeiboUrlQueue.addElement(star.getWeiId());
        }
    }
    public void getAllWeiBo(String weiid){
        Document doc = null;
        int pageNumber= 1;
        while(true) {
            doc = crawlOnePageWeiBo(weiid, pageNumber);
            if(doc.html().contains("\"msg\":\""))
                break;
            else {
                InsertMySQL.insertAllWeiBo(getElement(doc,weiid));
                weibos.clear();
            }
            pageNumber++;
            int random = (int)(1000*Math.random());
            random = random+800;
            if(random>1500)
                random = random - 500;
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Weibo> getElement(Document doc,String weiid){
        String OneWeiBo = "1314520",postTime = "1314520",content = "1314520",repost = "1314520",
                laud = "1314520",comment = "1314520",recontent = "1314520";
        String docHtml = doc.html();
        Matcher matcherWeiBo= publicMatcher(docHtml,Contants.regexWeiBo);
        while(matcherWeiBo.find()){
            Weibo wb = new Weibo();
            OneWeiBo = matcherWeiBo.group(1);
            postTime=publicMethod.patternWeiBoMethod(Contants.regexWeiBoPostTime,OneWeiBo);
            content= publicMethod.patternWeiBoMethod(Contants.regexWeiBoContent,OneWeiBo);
            repost = publicMethod.patternWeiBoMethod(Contants.regexWeiBoFollow,OneWeiBo);
            laud = publicMethod.patternWeiBoMethod(Contants.regexWeiBoLaud,OneWeiBo);
            comment = publicMethod.patternWeiBoMethod(Contants.regexWeiBoComment,OneWeiBo);
            boolean isRepost= (OneWeiBo.contains("page_id")==true)?true:false;
            boolean hasPic = (OneWeiBo.contains("original_pic")==true)?true:false;
            if(isRepost == true)
                recontent = publicMethod.patternWeiBoMethod(Contants.regexWeiBoReContext,OneWeiBo);
            wb.setWeiId(weiid);
            wb.setPostTime(publicMethod.decodeUnicode(postTime));
            wb.setContent(publicMethod.decodeUnicode(content));
            wb.setRecontext(publicMethod.decodeUnicode(recontent));
            wb.setRepostNum(Integer.parseInt(repost));
            wb.setLaud(Integer.parseInt(laud));
            wb.setComment(Integer.parseInt(comment));
            wb.setIsRepost(isRepost);
            wb.setHasPic(hasPic);
            wb.setWeiboTH(weiboTH);
            weiboTH++;
            InsertMySQL.NativeFile(wb);
            weibos.add(wb);
        }
        return weibos;
    }
    public Matcher publicMatcher(String docHtml,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(docHtml);
        return matcher;
    }
    public Document crawlOnePageWeiBo(String weiid,int i){
        String url = "http://m.weibo.cn/page/tpl?containerid=100505"+weiid+"_-_WEIBO_SECOND_PROFILE_WEIBO&page="+i;
        System.out.println(i+"、"+url);
        String content = null;
        HttpResponse response = null;
        reConnection:for (int j = 1; j <= 3; j++) {
            // 获得信息载体
            try {
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
                HttpConnectionParams.setSoTimeout(params, 10 * 1000);
                AbstractHttpClient httpClient = new DefaultHttpClient(params);

                HttpGet getHttp = new HttpGet(url);
                // 设置HTTP Header
                getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
                response = httpClient.execute(getHttp);

                break;
            } catch (Exception e) {
                System.err.println("访问出错,重新访问第"+j+"次");
                if(j==3){
                    System.out.println("写入路径到本地：E:\\FilmData\\star\\noVisitUrls.txt");
                    urls.add("E:\\FilmData\\star\\noVisitUrls.txt");
                    NativeFile("E:\\FilmData\\star\\noVisitUrls.txt",url);
                    try {
                        Thread.sleep(1000*60*10);
                        j = 1;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                continue reConnection;
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
    public boolean NativeFile(String path,String url) {
        File fileWrite= new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileWrite, true);
        } catch (IOException e) {
            return true;
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(url);
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

