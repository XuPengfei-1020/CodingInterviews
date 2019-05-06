package chapter2;

/**
 * 单例模式，在加载类的时候创建。
 * @author flying
 */
public class Q2_Singleton2 {
    public final static Q2_Singleton2 instance = new Q2_Singleton2();

    /**
     * 构造函数，私有化。
     */
    private Q2_Singleton2() {
    }
}