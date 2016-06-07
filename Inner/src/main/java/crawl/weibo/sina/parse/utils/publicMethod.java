package crawl.weibo.sina.parse.utils;

import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seasen on 2016/3/10.
 */
public class publicMethod {
    public static String  patternMethod(String regex, Document doc){
        String common = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherCommon= pattern.matcher(doc.html());
        try{
            if (matcherCommon.find()){
                common = matcherCommon.group(1);
            }
        }catch(Exception e){
            return "";
        }
        return common;
    }
    public static String patternWeiBoMethod(String regex, String str){
        String common = "1314520";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherCommon= pattern.matcher(str);
        try {
            if (matcherCommon.find())
                common = matcherCommon.group(1);
        }catch(Exception e){return "1314520";}
        return common;
    }
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
