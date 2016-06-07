package crawl.weibo.sina.parse.bean;

/**
 * Created by seasen on 2016/3/5.
 */
public class Stars {
    private String name = null;
    private String weiId ;
    private int id;
    private String sex = "1";
    private int fansNum = 0;
    private int tieNum = 0;
    private int follow = 0;
    private int weiboNum = 0;
    private String nickName = null;
    private String briefIntro = null;
    public String getWeiId() {
        return weiId;
    }

    public void setWeiId(String weiId) {
        this.weiId = weiId;
    }

    public int getTieNum() {
        return tieNum;
    }

    public void setTieNum(int tieNum) {
        this.tieNum = tieNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getWeiboNum() {
        return weiboNum;
    }

    public void setWeiboNum(int weiboNum) {
        this.weiboNum = weiboNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
