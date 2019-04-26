package cn.evan.ssm.study.JavaLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLock {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    public static Object get(String key) {
        r.lock();
        try {
            System.out.println("读取锁key：" + key + "开始");
            Thread.sleep(100);
            Object o = map.get(key);
            System.out.println("读取锁key：" + key + "结束");
            System.out.println();
            return  o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            r.unlock();
        }
        return null;
    }

    public static Object set(String key,Object value) {
        w.lock();
        try {
            System.out.println("写锁key：" + key + "开始");
            Thread.sleep(100);
            Object o = map.put(key,value);
            System.out.println("写锁key：" + key + "结束");
            System.out.println();
            return  o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            w.unlock();
        }
        return  null;
    }

    public static final  void clear(){
        map.clear();
    }

    public static void main(String[] args) {
      /*  AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.accumulateAndGet();*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReadAndWriteLock.set(i+"",i+"");
                }
            }
        }).start();

        new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReadAndWriteLock.get(i+"");
                }
            }
        }).start();
    }
}
