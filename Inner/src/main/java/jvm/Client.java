package jvm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by seasen on 2016/1/27.
 */
public class Client {
    public final AtomicLong count = new AtomicLong(0);

    class A{
         void method(){}
    }
    public static void main(String[] args) {

        Singleton01 s1 = Singleton01.getInstance();
        Singleton01 s2 = Singleton01.getInstance();
        Singleton01 s3 = Singleton01.getInstance();
         Client c1 = new Client();
         Client c2 = new Client();
         Client c3 = new Client();

        System.out.println("s1 == s2 : " + (s1 == s2));
        System.out.println("s1 == s3 : " + (s1 == s3));
        System.out.println("s2 == s3 : " + (s2 == s3));
        System.out.println("c1 == c2 : " + (c1 == c2));
        System.out.println("c1 == c3 : " + (c1 == c3));
        System.out.println("c2 == c3 : " + (c2 == c3));
    }
}