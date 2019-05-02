package cn.evan.ssm.study;


public class CreatThreadDemo1 extends  Thread {
    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("run,i:"+i);
        }
    }

    public static void main(String[] args) {
        /*CreatThreadDemo1 creatThreadDemo1=new CreatThreadDemo1();
        creatThreadDemo1.start();*/
        demo2();
    }

    public void demoTest(){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<60;i++){
                   /* try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("子线程i:"+i);
                }
            }
        }).start();
        for (int i=1;i<30;i++){
            System.out.println("主线程i:"+i);
        }
        System.out.println("主线程执行完毕");
    }


    public static void demo1(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 60; i++) {
                    System.out.println("子线程i:" + i);
                }
            }
        });
        thread.start();
        for (int i = 1; i < 60; i++) {
            System.out.println("主线程i:" + i);
        }
    }

    public static void demo2(){
        final Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<20;i++){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T1,i:"+i);
                }
            }
        });
        t1.start();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<20;i++){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T2,i:"+i);
                }
            }
        });
        t2.start();
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<20;i++){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T3,i:"+i);
                }
            }
        });
        t3.start();
    }
}
