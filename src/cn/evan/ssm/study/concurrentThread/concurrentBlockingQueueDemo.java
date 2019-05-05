package cn.evan.ssm.study.concurrentThread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class concurrentBlockingQueueDemo {
    final BlockingQueue<String> queue = new LinkedBlockingDeque<>();
    final static Random random = new Random();

    public static void main(String[] args) {
        final concurrentBlockingQueueDemo concurrentBlockingQueueDemo = new concurrentBlockingQueueDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        concurrentBlockingQueueDemo.queue.put("value" + random.nextInt(10000));
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "producer").start();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "-" + concurrentBlockingQueueDemo.queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "consumer" + i).start();
        }
    }
}
