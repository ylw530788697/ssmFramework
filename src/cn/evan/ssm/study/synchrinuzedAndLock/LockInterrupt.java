package cn.evan.ssm.study.synchrinuzedAndLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author v_liwenyang
 */
public class LockInterrupt {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterrupt lockInterrupt = new LockInterrupt();
        myThread myThread=new myThread(lockInterrupt);
        myThread myThread1=new myThread(lockInterrupt);
        myThread.start();
        myThread1.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        myThread1.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        //如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE) {
                    break;
                }
                //插入数据
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }

    }
}

class myThread extends Thread {
    private LockInterrupt lockInterrupt;

    public myThread(LockInterrupt lockInterrupt) {
        this.lockInterrupt = lockInterrupt;
    }

    @Override
    public void run() {
        try {
            lockInterrupt.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
    }
}
