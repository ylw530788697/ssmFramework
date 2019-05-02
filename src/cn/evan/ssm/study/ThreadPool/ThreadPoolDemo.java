package cn.evan.ssm.study.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //1,可缓存得线程池
       /* ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            int temp=i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:" + Thread.currentThread().getName()+",i:"+temp);
                }
            });
        }*/
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //pool.submit();
        //pool.submit()
        for (int i = 0; i < 20; i++) {
            final int temp=i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:" + Thread.currentThread().getName()+",i:"+temp);
                }
            });
        }
      /*  ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 20; i++) {
            int temp=i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:" + Thread.currentThread().getName()+",i:"+temp);
                }
            },3,TimeUnit.SECONDS);
        }*/
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            final int temp=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:" + Thread.currentThread().getName()+",i:"+temp);
                }
            });
        }
    }
}
