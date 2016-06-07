package crawl.weibo.sina.parse.utils;

/**
 * Created by seasen on 2016/3/5.
 */
public class Contants {
    public static String ACCOUNT_PATH;
    public static String WEIBO_BASE_STR = "http://weibo.cn/u/";
    public static String REPOST_LOG_PATH;
    public static String ABNORMAL_ACCOUNT_PATH;
    public static String OK = "true";
    public static String TIMEOUT = "TIMEOUT";
    public static String CHECK_WEIBO_NUM;
    public static String regex;
    public static String regexRepostNum = "(.*?)";
    public static String regexComment = "(.*?)";
    public static String regexWeiboTH = "(.*?)";
    public static String regexNickName = "name\":\"(.*?)\",\"avatar_hd";
    public static String regexWeiboNum = "mblogNum\":\"(.*?)\",\"fansNum";
    public static String regexFollow = "attNum\":\"(.*?)\",\"nativePlace";//明星转发
    public static String regexWeiBo = "\"card_type\":(.*?)\"attitudes_status\"";//一条微博所有信息
    public static String regexWeiBoFollow = "reposts_count\":(.*?),\"comments_count";//微博转发量
    public static String regexWeiBoPostTime = "created_at\":\"(.*?)\",\"id\"";//微博发布时间
    public static String regexWeiBoContent = "text\":\"(.*?)\"source_allowclick\"";//微博内容
    public static String regexWeiBoComment = "comments_count\":(.*?),\"attitudes_count";//微博评论量
    public static String regexWeiBoLaud = "attitudes_count\":(.*?),\"isLongText";//微博点赞量
    public static String regexWeiBoReContext = "page_desc\":\"(.*?)\",\"object_type";//微博转发内容
    public static String regexFansNum = "fansNum\":\"(.*?)\",\"following";
    public static String regexBriefIntro = "verified_reason\":\"(.*?)\",\"name";
}
