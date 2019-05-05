package cn.evan.ssm.study.concurrentThread;

import sun.print.PeekGraphics;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/*
*基础链表
*/
public class concurrentLinkedQueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.offer("value"+i);
        }
        System.out.println(queue);
        System.out.println(queue.size());
        //Ppeek 查看queue中的首数据
        System.out.println(queue.peek());
        System.out.println(queue.size());

        //获取queue中的首数据
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

}
