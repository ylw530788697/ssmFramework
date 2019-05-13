package cn.evan.ssm.study.ThreadBingFa;

import cn.evan.ssm.study.productAndComsum.BlockingDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread {
    private BlockingQueue queue;
    private boolean flag = true;
    private static AtomicInteger count = new AtomicInteger();

    public ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    public ProducerThread(BlockingDemo blockingDemo) {
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动....");
        try {
            while (flag) {
                System.out.println("正在生产队列");
                String data = count.incrementAndGet() + "";
                //将数据存入队列
                boolean offer = queue.offer(data, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("生产者,存入" + data + "到队列中,成功.");
                } else {
                    System.out.println("生产者,存入" + data + "到队列中,失败.");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者退出线程");
        }
    }
}
