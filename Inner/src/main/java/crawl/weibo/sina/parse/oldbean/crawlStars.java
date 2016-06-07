package crawl.weibo.sina.parse.oldbean;

import crawl.weibo.sina.parse.parser.parserWeiBo;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import static crawl.weibo.sina.parse.parser.InsertMySQL.NativeFile;
/**
 * Created by seasen on 2016/3/19.
 */
public class crawlStars {
    public static void main(String[] args) {
        crawlStars crawl = new crawlStars();
        crawl.crawlChina(3,50);
        crawl.crawlChina(2,40);
        crawl.crawlChina(1,336);


    }
    static int number = 1;
    public  void crawlChina(int nation,int page){
        parserWeiBo pwb = new parserWeiBo();
        String content = null;
        HttpResponse response = null;
        for (int j = 1; j <= page; j++) {
            // 获得信息载体
            try {
                String url = "http://ku.ent.sina.com.cn/star/search&nationality="+nation+"&profession=%E6%BC%94%E5%91%98&page_no="+j;
                System.out.println(url);
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
                HttpConnectionParams.setSoTimeout(params, 10 * 1000);
                AbstractHttpClient httpClient = new DefaultHttpClient(params);
                HttpGet getHttp = new HttpGet(url);
                // 设置HTTP Header
                getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
                response = httpClient.execute(getHttp);
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
                Elements elements = doc.getElementsByTag("img");
                int n = elements.size()-6;
                for (Element e:elements) {
                    if(n>0) {
                        if(e.attr("title")!=""||e.attr("title")!=null) {
                            System.out.println(number + "、" + e.attr("title"));
                            pwb.NativeFile("E:\\FilmData\\star\\stars.txt", e.attr("title"));
                            number++;
                        }
                    }
                    n--;
                }
            } catch (Exception e) {
                System.err.println("访问出错,重新访问第"+j+"次");
                }
            }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
