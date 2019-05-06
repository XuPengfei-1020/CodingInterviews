package chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单例模式，按需创建。推荐。
 *
 * @author flying
 */
public class Q2_Singleton1 {
    private volatile static Q2_Singleton1 instance;
    private static Lock lock = new ReentrantLock();

    /**
     * 按需创建模式
     * @return
     */
    public static Q2_Singleton1 getInstance() {
        if (instance != null) {
            return instance;
        }

        lock.lock();

        try {
            if (instance != null) {
                return instance;
            }

            return instance = new Q2_Singleton1();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 构造函数，私有化。
     */
    private Q2_Singleton1() {
    }
}