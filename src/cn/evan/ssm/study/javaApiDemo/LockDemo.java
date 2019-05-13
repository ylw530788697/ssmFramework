package cn.evan.ssm.study.javaApiDemo;

public class LockDemo {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        Thread thread1=new Thread(ticket,"1111");
        Thread thread2=new Thread(ticket,"222");
        Thread thread3=new Thread(ticket,"333");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
