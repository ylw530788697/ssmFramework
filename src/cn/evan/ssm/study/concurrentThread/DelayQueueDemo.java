package cn.evan.ssm.study.concurrentThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    static BlockingQueue<Mytask> queue=new DelayQueue<>();
    public static void main(String[] args) throws InterruptedException {
        long value=System.currentTimeMillis();
        System.out.println(value);
        Mytask task1=new Mytask(value+2000);
        Mytask task2=new Mytask(value+1000);
        Mytask task3=new Mytask(value+3000);
        Mytask task4=new Mytask(value+2500);
        Mytask task5=new Mytask(value+1500);
        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        queue.put(task4);
        queue.put(task5);
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }
    }
}

class Mytask implements Delayed{
    private long compareValue;

    public Mytask(long compareValue){
        this.compareValue=compareValue;
    }

    /**
     * 获取计划时长的方法
     * 根据参数timeUnit来决定 如何返回结果值
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        int convert = (int) unit.convert(compareValue - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        return convert;
    }


    /**
     * 比较大小。自动实现升序
     * 建议和getDelay方法配合完成
     * 如果在DelayQueue是需要按时间完成的计划任务，必须配合getDelay方法完成
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        int i = (int)(this.getDelay(TimeUnit.MICROSECONDS) - o.getDelay(TimeUnit.MICROSECONDS));
        return i;
    }

    @Override
    public String toString() {
        return "Mytask{" +
                "compareValue=" + compareValue +
                '}';
    }
}
