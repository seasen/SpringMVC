package Java.Basics;/*
 * This example shows how to use Java to build http connection and request
 * the ltp-cloud service for perform full-stack Chinese language analysis
 * and get results in specified formats
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SimpleAPI {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || !(args[0].equals("xml") 
                                || args[0].equals("json") 
                                || args[0].equals("conll"))) {
            System.out.println("Usage: java SimpleAPI [xml/json/conll]");
            return;
        }

        String api_key = "q553k7c26JHPhwvqFt3eUPbinmiN3VndkTWZBygF";
        String pattern = "all";
        String format  = args[0];
        String text    = "我爱北京天安门。";
        text = URLEncoder.encode(text, "utf-8");

        URL url     = new URL("http://ltpapi.voicecloud.cn/analysis/?"
                              + "api_key=" + api_key + "&"
                              + "text="    + text    + "&"
                              + "format="  + format  + "&"
                              + "pattern=" + pattern);
        URLConnection conn = url.openConnection();
        conn.connect();

        BufferedReader innet = new BufferedReader(new InputStreamReader(
                                conn.getInputStream(),
                                "utf-8"));
        String line;
        while ((line = innet.readLine())!= null) {
            System.out.println(line);
        }
        innet.close();
    }
}
