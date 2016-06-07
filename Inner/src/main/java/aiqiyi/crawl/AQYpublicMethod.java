package aiqiyi.crawl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by seasen on 2016/5/23.
 */
public class AQYpublicMethod {
    public static String  patternMethod(String content){
        Pattern pattern = Pattern.compile(AiQiYiConstant.regexContent);
        Matcher matcherCommon= pattern.matcher(content);
        try{
            if (matcherCommon.find()){
                content = matcherCommon.group(1);
            }
        }catch(Exception e){
            return "";
        }
        return content;
    }
}
