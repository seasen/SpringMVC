package jvm;

/**
 * Created by seasen on 2016/1/27.
 */
class Singleton01 {
    private static Singleton01 singleton = null;
    // 私有的构造函数，限制外部环境的非法创建和访问
    private Singleton01() {
        //一些初始化操作
    }
    // 静态方法，用于创建单例类的“唯一”实例
    public static Singleton01 getInstance() {
        if (singleton == null) {
            singleton = new Singleton01();
        }
        return singleton;
    }
    // 单例类也需要提供其他静态方法给外部环境访问，完成一定的服务
    public void doSomethings() {
        System.out.println("do somethings ...");
    }
}