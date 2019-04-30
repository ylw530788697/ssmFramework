package cn.evan.ssm.study.concurrentThread;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContainerDemo<E> {
    private Lock lock=new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();
    private int count=0;
    private final int MAX=10;
    private final LinkedList<E> list=new LinkedList<>();
    public int getCount(){
        return count;
    }

    public void put(E e){
        lock.lock();
        try {
            while (list.size()==MAX) {
                System.out.println(Thread.currentThread().getName() + "等待....");
                //进入等待队列。释放锁标记
                //借助条件，进入的等待队列
                producer.await();
            }
            System.out.println(Thread.currentThread().getName()+"put....");
            list.add(e);
            count++;
            //借助条件唤醒所有的消费者
            consumer.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public E get(){
        E e=null;
        lock.lock();
        try {
            while (list.size()==0){
                System.out.println(Thread.currentThread().getName()+"等待。。。。");
                consumer.await();
            }
            System.out.println(Thread.currentThread().getName()+"get...");
            e=list.removeFirst();
            count--;
            //借助条件唤醒所有生产者
            producer.signalAll();
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }finally {
            lock.unlock();
        }
        return  e;
    }

    public static void main(String[] args) {
        final ContainerDemo<String> objectContainerDemo = new ContainerDemo<>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(objectContainerDemo.get());
                    }
                }
            },"consumer"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 25; j++) {
                        objectContainerDemo.put("countainer value"+j);
                    }
                }
            },"producer"+i).start();
        }
    }
}
