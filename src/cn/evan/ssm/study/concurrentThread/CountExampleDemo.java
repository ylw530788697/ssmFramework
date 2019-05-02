package cn.evan.ssm.study.concurrentThread;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CountExampleDemo {
    private static  int threadPool=200;
    private static int clientTotal=5000;
    private static long count=0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(threadPool);
        for (int i = 0; i < clientTotal; i++) {
            //executorService.execute();
        }
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        }).start();
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();

        runDemo runDemo = new runDemo();
        Thread thread = new Thread(runDemo);
        thread.start();

        System.out.println();*/
    }

}
class ThreadDemo extends  Thread{
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+":"+"线程测试");
    }
}
class runDemo implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+"接口runnable测试");
    }
}
