package aiqiyi.crawl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by seasen on 2016/5/23.
 */
public class AiQiYi {
    public static void main(String[] args) {
        AiQiYi aiQiYi = new AiQiYi();
        aiQiYi.getContent(1);
    }
    public HashMap<String,String> getCommentElement(String content){
        HashMap<String ,String> hashMap = new HashMap<String, String>();



        return null;
    }
    public String  getContent(int i){
        String url = "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?aid=11036036&albumid=202383801&categoryid=2" +
                "&cb=fnsucc&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=" +i+
                "&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&qitanid=11036036&" +
                "qypid=01010011010000000000&reply_sort=hot&sort=add_time&t=0.27167045519493094&tvid=342022400";
        String content = null;
        HttpResponse response = null;
        reConnection:for (int j = 1; j <= 3; j++) {
            try {
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
                HttpConnectionParams.setSoTimeout(params, 10 * 1000);
                AbstractHttpClient httpClient = new DefaultHttpClient(params);
                HttpGet getHttp = new HttpGet(url);
                getHttp.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
                response = httpClient.execute(getHttp);
                break;
            } catch (Exception e) {
                System.err.println("访问出错,重新访问第"+j+"次");
                if(j==3){
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
        content = AQYpublicMethod.patternMethod(content);
        System.out.println(content);

       /* JSONArray json = JSONArray.fromObject(content);
        String[] a = new String[json.length()];
        for (int j = 0; j < json.length(); j++) {
            a[j] = json.getJSONObject(j).getString("contentId");
            System.out.print(a[j]+" ");
        }*/
        return content;
    }
}
