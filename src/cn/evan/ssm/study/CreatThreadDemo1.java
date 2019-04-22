package cn.evan.ssm.study;

public class CreatThreadDemo1 extends  Thread {
    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("run,i:"+i);
        }
    }

    public static void main(String[] args) {
        CreatThreadDemo1 creatThreadDemo1=new CreatThreadDemo1();
        creatThreadDemo1.start();
        new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<30;i++){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程i:"+i);
                }
            }
        }).start();
        for (int i=1;i<30;i++){
            System.out.println("主线程i:"+i);
        }
        System.out.println("主线程执行完毕");
    }
}
