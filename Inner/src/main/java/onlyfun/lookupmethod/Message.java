package onlyfun.lookupmethod;

import java.util.Date;

/**
 * Created by seasen on 2016/1/10.
 */
public class Message {
    private String sysMessage;
    public Message() {
        sysMessage = "系统咨询："+new Date().toString();
        System.out.println(sysMessage);
    }
    public String toString(){
        return sysMessage;
    }
}
