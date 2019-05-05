package cn.evan.ssm.study.concurrentThread;

import java.util.concurrent.TimeUnit;

/*
*
*
* */
public class ThreadLocalDemo {
    volatile static String name="zhangSan";
    static ThreadLocal<String> threadLocal= new  ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
                System.out.println(threadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                name="lisi";
                threadLocal.set("wangWu");
            }
        }).start();
    }
}
