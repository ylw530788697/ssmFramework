package cn.evan.ssm.study.productAndComsum;

public class ProductDemo extends  Thread{
    private BlockingDemo blockingDemo;
    public ProductDemo(String name,BlockingDemo blockingDemo) {
        super(name);
        this.blockingDemo=blockingDemo;
    }

    @Override
    public void run() {
        int count=0;
        while (true){
            //synchronized (blockingDemo){
                if (blockingDemo.flag==true){
                    try {
                        blockingDemo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count%2==0){
                    blockingDemo.setBaoZi("人肉包子");
                }else{
                    blockingDemo.setBaoZi("菜包子");
                }
                count++;
                System.out.println(Thread.currentThread().getName()+"生产"+blockingDemo.getBaoZi());
                blockingDemo.flag=true;
                blockingDemo.notify();
            //}
        }
    }
}
