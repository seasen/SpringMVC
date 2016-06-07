package Java.Alogrithm;
import java.lang.reflect.*;
public class Test2{


    public static void main(String[] args) {
        String str = "hell";
        str += 'a';
        try {
            User user = new User();
            Class clazz = user.getClass();
            Method method = clazz.getDeclaredMethod("setAge",int.class);
            method.invoke(user,15);
            System.out.println(user.getAge());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
