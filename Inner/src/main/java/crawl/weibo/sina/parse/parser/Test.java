package crawl.weibo.sina.parse.parser;
import org.apache.http.Header;
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
import java.util.Locale;

/**
 * Created by seasen on 2016/3/9.
 */
public class Test {
    public static void main(String[] args)  {

            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
            HttpConnectionParams.setSoTimeout(params, 10 * 1000);
            AbstractHttpClient httpClient = new DefaultHttpClient(params);
            String url = "http://people.mtime.com/2064404/filmographies/";
            HttpGet getHttp = new HttpGet(url);
            // 设置HTTP Header
            getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");

            HttpResponse response = null;
        try {
            Document doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response = httpClient.execute(getHttp);
            Header[] local = response.getAllHeaders();
            /*for (int i = 0; i <local.length ; i++) {
                System.out.println(local[i]);
            }*/

            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "UTF-8");
           // System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
