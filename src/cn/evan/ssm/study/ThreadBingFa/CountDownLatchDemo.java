package cn.evan.ssm.study.ThreadBingFa;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("等待子线程执行完毕");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程，"+Thread.currentThread().getName()+"开始执行.....");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//每次减一
                System.out.println("子线程，"+Thread.currentThread().getName()+"结束执行.....");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程，"+Thread.currentThread().getName()+"开始执行.....");
                countDownLatch.countDown();//每次减一
                System.out.println("子线程，"+Thread.currentThread().getName()+"结束执行.....");
            }
        }).start();
        countDownLatch.await();
        for (int i = 0; i < 30; i++) {
            System.out.println( Thread.currentThread().getName()+"执行："+i);
        }
    }

}
