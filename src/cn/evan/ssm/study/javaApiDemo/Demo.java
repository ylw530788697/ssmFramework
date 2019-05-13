package cn.evan.ssm.study.javaApiDemo;

import java.util.concurrent.TimeUnit;

public class Demo {

    private static  String[] names={"bill","hill","jill"};

    public static void main(String[] args) {
      /*  try {
            checkUserName("hill");
            System.out.println("注册成功");
        } catch (LoginException e) {
            e.printStackTrace();
        }*/
      ThreadDemo threadDemo=new ThreadDemo();
      threadDemo.run();
      RunDemo runDemo=new RunDemo();
      Thread thread=new Thread(runDemo);
      thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main:"+i);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkUserName(String uname) throws LoginException {
        for (String name:names
             ) {
            if (uname.equals(name)){
                throw new LoginException("dear:"+uname+"已经被注册了");
            }
        }
        return true;
    }
}

class ThreadDemo extends  Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread"+":"+i);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RunDemo implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("run:"+i);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
