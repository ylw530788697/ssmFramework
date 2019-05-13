package cn.evan.ssm.study.productAndComsum;

import cn.evan.ssm.study.ThreadBingFa.ProducerThread;

public class PruductAndConsumDemo {
    public static void main(String[] args) {
        BlockingDemo blockingDemo=new BlockingDemo();
        ProductDemo productDemo=new ProductDemo("生产者",blockingDemo);
        ConsumDemo consumDemo=new ConsumDemo("消费者",blockingDemo);
        productDemo.start();
        consumDemo.start();

    }
}
