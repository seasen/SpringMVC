package crawl.weibo.sina.parse.parser;

import crawl.weibo.sina.parse.bean.Stars;
import crawl.weibo.sina.parse.oldbean.newStars;
import crawl.weibo.sina.parse.worker.GetInfor;
import crawl.weibo.sina.parse.worker.LoginWeibo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seasen on 2016/3/6.
 */
public class StarID {
    //http://m.weibo.cn/searchs
    public static final Logger log = Logger.getLogger(StarID.class);
    public static int urlNum = 1;
    public static void main(String[] args) throws IOException {
        StarID s = new StarID();
        s.starNameId();
    }
    /**
     * 获得明星的微博ID号
     * */
    public  List<Stars> starNameId() throws IOException {
        parserWeiBo pwb = new parserWeiBo();
        List<Stars> id = new ArrayList<Stars>();
        String content = null;
        Document contentDoc = null;
        List<Stars> namelist = GetInfor.getStarName();
        Iterator<Stars> it = namelist.iterator();
        // 设置GET超时时间
        reConnection:while (it.hasNext()) {
            Stars stars = new Stars();
            String url = "http://m.weibo.cn/main/pages/index?containerid=100103type%3D3%26q%3D";
            String url2 = "&type=user&queryVal=";
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //http://m.weibo.cn/main/pages/index?containerid=100103type%3D3%26q%3D
            String name = it.next().getName();
            url = url + name + url2 + name;
            System.out.println((urlNum++)+"、 "+url);

            HttpResponse response = null;
            HttpEntity entity = null;
            // 获得信息载体
            try {
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params, 100 * 1000);
                HttpConnectionParams.setSoTimeout(params, 100 * 1000);
                AbstractHttpClient httpClient = new DefaultHttpClient(params);
                HttpGet getHttp = new HttpGet(url);
                // 设置HTTP Header
                getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
                response = httpClient.execute(getHttp);
                entity = response.getEntity();
            } catch (Exception e) {
                System.out.println("访问出错");
                if (response == null) {
                    System.out.println("什么鬼？");
                }
                log.info(">> Error: 访问失败 - 明星名字："+name+"，");
                continue reConnection;
            }

                if (entity != null) {
                    // 转化为文本信息, 设置爬取网页的字符集，防止乱码
                    content = EntityUtils.toString(entity, "UTF-8");
                }
            contentDoc = getPageDocument(content);
            String starId = getGoalContent(contentDoc);
            stars.setName(name);
            stars.setWeiId(starId);
            id.add(stars);
            System.out.println(starId);
            pwb.NativeFile("E:\\FilmData\\star\\starAndWeiId.txt",name+"\t"+starId);
        }
            return id;
    }

    public Document getPageDocument(String content){
        return Jsoup.parse(content);
    }
    public String getGoalContent(Document doc) {
        String starId = "";
        String regexId = "\"id\":(.*?),\"screen_name";
        Pattern patternId = Pattern.compile(regexId);
        Matcher matcherId = patternId.matcher(doc.html());
        if(matcherId.find()){
            starId=matcherId.group(1);
        }
        return starId;
    }
    public boolean CheckWeiBo(String starNum)  {
        if(starNum.contains("\\")){
            String[] str = starNum.split("\\\\");
            System.out.println(str[0]);
            if(Integer.parseInt(str[0])>50)
                return true;
        }
        return false;

    }
      /*  for (int i = 0; i <e.size() ; i++) {
            System.out.println(e.get(i));
        }*/
       /* Elements elements = doc.getElementsByClass("c");
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).id().startsWith("C_")){
                commentItems.add(elements.get(i));
            }
        }
*/
}
