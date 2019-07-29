package cn.evan.ssm.study.synchrinuzedAndLock;

import com.sun.javafx.image.BytePixelSetter;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author v_liwenyang
 */
public class LockTest {
    private ArrayList<Integer> arrayList=new ArrayList<Integer>();
    Lock lock=new ReentrantLock();
    public static void main(String[] args) {
        final LockTest test=new LockTest();
        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            //lock.lock();
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 50; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {

            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
