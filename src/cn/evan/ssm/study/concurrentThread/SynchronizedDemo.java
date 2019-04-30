package cn.evan.ssm.study.concurrentThread;

import java.util.concurrent.TimeUnit;

/**
 * @author evanYang
 */
public class SynchronizedDemo {
    private int count = 0;
    private Object o = new Object();

    public void testSyn1() {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "count=" + count++);
        }
    }

    public void testSyn2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "count=" + count++);
        }
    }

    public synchronized void testSyn3() {

        System.out.println(Thread.currentThread().getName() + "count=" + count++);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        //while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedDemo.testSyn3();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedDemo.testSyn3();
                }
            }).start();

    }

}
