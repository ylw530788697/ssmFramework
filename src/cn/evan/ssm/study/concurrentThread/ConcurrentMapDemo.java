package cn.evan.ssm.study.concurrentThread;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentMapDemo {

    public static void main(String[] args) {
        //final Map<String, String> map = new Hashtable<>();
        final Map<String, String> map = new ConcurrentHashMap<>();
        final Random random = new Random();
        Thread[] array = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(array.length);
        long begin=System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        map.put("key" + random.nextInt(1000000), "value" + random.nextInt(1000000));
                    }
                    latch.countDown();
                }
            });
        }
        for (Thread t : array
                ) {
            t.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end =System.currentTimeMillis();
        System.out.println(end-begin);


    }
}
