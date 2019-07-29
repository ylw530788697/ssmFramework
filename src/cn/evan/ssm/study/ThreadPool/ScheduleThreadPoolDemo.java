package cn.evan.ssm.study.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author v_liwenyang
 */
public class ScheduleThreadPoolDemo {
/*    public static void main(String[] args) {
        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //延迟1秒执行
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("延迟1秒执行");
            }
        }, 1, TimeUnit.SECONDS);
    }*/

    public static void main(String[] args) {
        double v = 0.123;

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟一秒每三秒执行一次");
            }
        }, 5, 3, TimeUnit.SECONDS);
    }
}
