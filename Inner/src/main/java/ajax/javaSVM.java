package ajax;

/**
 * Created by seasen on 2016/1/18.
 */
public class javaSVM {
    public static void main(String[] args) {
        javaSVM jsvm = new javaSVM();
        jsvm.javaIntern();
    }
    public void javaIntern(){
        String str1 = new StringBuilder("计算机").append("搞笑").toString();
        System.out.println(str1.intern());
        System.out.println(str1.intern() ==str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() ==str2);
    }
}
