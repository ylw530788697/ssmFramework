package cn.evan.ssm.study.productAndComsum;

public class ConsumDemo extends Thread {
    private BlockingDemo blockingDemo;
    public ConsumDemo(String name,BlockingDemo blockingDemo){
        super(name);
        this.blockingDemo=blockingDemo;
    }

    @Override
    public void run() {
        while (true){
            //synchronized (blockingDemo){
                if (blockingDemo.flag==false){
                    try {
                        blockingDemo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"消费"+blockingDemo.getBaoZi());
                blockingDemo.flag=false;
                blockingDemo.notify();
            //}
        }
    }
}
