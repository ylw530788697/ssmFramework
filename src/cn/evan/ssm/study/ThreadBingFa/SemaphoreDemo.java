package cn.evan.ssm.study.ThreadBingFa;

import java.util.Random;
import java.util.concurrent.Semaphore;

class parent extends Thread{
    Semaphore wc;
    String name;

    public parent(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        int i = wc.availablePermits();
        if (i>0){
            System.out.println(name+"hahah 有茅坑");
        }else{
            System.out.println(name+"没有坑啦");
        }
        try {
            wc.acquire();
            System.out.println(name+"我终于能上厕所啦");
            Thread.sleep(new Random().nextInt(100));
            System.out.println(name+"厕所上完啦");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放茅坑
            wc.release();
        }
    }
}
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <10 ; i++) {
            new parent(semaphore,"弟"+i+"个").start();
        }
    }
}
