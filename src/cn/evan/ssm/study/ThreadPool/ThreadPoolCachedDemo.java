package cn.evan.ssm.study.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author v_liwenyang
 */
public class ThreadPoolCachedDemo {

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            //try {
            //    Thread.sleep(1000);
            //}catch (InterruptedException e){
            //    e.printStackTrace();
            //}
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在被执行");
                }
            });
        }


    }
}
