package crawl.weibo.sina.parse.worker;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seasen on 2016/3/5.
 */
public class LoginWeiboBackup {
    public static void main(String[] args) {
        Connection con = Jsoup.connect("http://service.channel.mtime.com/service/search.mcs?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Channel.Pages.SearchService&Ajax_CallBackMethod=SearchPersonByPersonRegion&Ajax_CrossDomain=2&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2Fpeople%2Fsearch%2Ffocus%2F%23sortType%3D4%26listType%3D0%26r%3D1%26pageIndex%3D1&t=20164510561949180&Ajax_CallBackArgument0=&Ajax_CallBackArgument1=0&Ajax_CallBackArgument2=1&Ajax_CallBackArgument3=4&Ajax_CallBackArgument4=1&Ajax_CallBackArgument5=0");
        try {
            Document doc = con.ignoreContentType(true).get();
            String regex = "table(.*)t_r ";
           /* String tmp = "<input type=\"\\&quot;hidden\\&quot;\" id=\"\\&quot;divSearchPersonByPersonRegionPagerprevPageName\\&quot;\" value=\"\\&quot;上一页\\&quot;/\">\n" +
                    "  <input type=\"\\&quot;hidden\\&quot;\" id=\"\\&quot;divSearchPersonByPersonRegionPagernextPageName\\&quot;\" value=\"\\&quot;下一页\\&quot;/\">\n" +
                    "  <input type=\"\\&quot;hidden\\&quot;\" id=\"\\&quot;divSearchPersonByPersonRegionPagercurrentPageName\\&quot;\" value=\"\\&quot;1\\&quot;/\">";
            *///&quot;
            String str = doc.html().replaceAll("\\\\&quot;","");
            str = str.replaceAll("\\\\","");
            System.out.println(str);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str.toString());

            while(matcher.find()){
                System.out.println(matcher.group());
                System.out.println("*************************************************");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
