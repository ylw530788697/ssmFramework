package cn.evan.ssm.study.ThreadBingFa;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class cyclicBarrierDemo extends Thread{
    private  CyclicBarrier cyclicBarrier;

    public cyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+",正在写入数据");
       /* try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("线程"+ Thread.currentThread().getName()+",写入数据成功");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("所有线程执行完毕。。。。。。。");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i <5 ; i++) {
            cyclicBarrierDemo cyclicBarrierDemo = new cyclicBarrierDemo(cyclicBarrier);
            cyclicBarrierDemo.start();
        }
    }
}
