package crawl.weibo.sina.parse.worker;

/**
 * 登陆微博，登陆成功返回true，失败返回false
 * Created by seasen on 2016/3/5.
 */
import java.io.UnsupportedEncodingException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

public class LoginWeibo {

    private static final Logger Log = Logger.getLogger(LoginWeibo.class.getName());
    public static final String LOGIN_HOST = "login.weibo.cn";
    public static final String LOGIN_URL = "https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=http%3A%2F%2Fm.weibo.cn%2F";
    public static final String PROFILE_URL = "http://weibo.cn/";
    private AbstractHttpClient client = new DefaultHttpClient();

    public static void main(String[] args) {
        LoginWeibo lw = new LoginWeibo();
        lw.loginAndGetConnection("953862461@qq.com","shaosen131");
    }
    public String loginAndGetConnection(String username, String password){

        String gsid = null;//wstgwps
        Connection.Response res = null;
        try {
            res = Jsoup.connect("https://passport.weibo.cn/signin/login")
                    .data("loginName", username, "loginPassword", password)
                    .method(Method.POST).execute();
        }
        catch(Exception e){
            Log.error(e);
            System.err.println("登陆失败");
            client.getConnectionManager().shutdown();
            return "false";
        }
        // 账号被禁，跳转到微博广场，所以返回null
        return "true";
    }
}
