package onlyfun.aop.introduction;

/**
 * Created by seasen on 2016/1/16.
 */
public class Some implements ISome {
    private String some;
    public void domSome() {
        System.out.println("the responsibility of original");
    }

    public void setSome(String some) {
        this.some = some;
    }

    public String getSome() {
        return some;
    }
}
