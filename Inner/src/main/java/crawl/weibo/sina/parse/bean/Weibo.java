package crawl.weibo.sina.parse.bean;

/**
 * Created by seasen on 2016/3/5.
 */
public class Weibo {
    private String id = null;
    private int weiboTH;
    private String WeiId = null;
    private String content = null;
    private String postTime = null;
    private boolean hasPic;
    private boolean isRepost;
    private String recontext;
    private int repostNum;
    private int comment;
    private int laud;
    private String idstr;
    public String getIdstr() {
        return idstr;
    }
    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }
    public int getWeiboTH() {
        return weiboTH;
    }
    public void setWeiboTH(int weiboTH) {
        this.weiboTH = weiboTH;
    }
    public String getWeiId() {
        return WeiId;
    }
    public void setWeiId(String weiId) {
        WeiId = weiId;
    }
    public int getRepostNum() {
        return repostNum;
    }
    public void setRepostNum(int repostNum) {
        this.repostNum = repostNum;
    }
    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLaud() {
        return laud;
    }

    public void setLaud(int laud) {
        this.laud = laud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public boolean getHasPic() {
        return hasPic;
    }

    public void setHasPic(boolean hasPic) {
        this.hasPic = hasPic;
    }

    public boolean getIsRepost() {
        return isRepost;
    }

    public void setIsRepost(boolean repost) {
        isRepost = repost;
    }

    public String getRecontext() {
        return recontext;
    }

    public void setRecontext(String recontext) {
        this.recontext = recontext;
    }
}
