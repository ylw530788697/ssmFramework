package cn.evan.ssm.study.transTickets;

public class TheadDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadTrans threadTrans = new ThreadTrans();
        Thread t1=new Thread(threadTrans,"窗口11111");
        Thread t2=new Thread(threadTrans,"窗口2");
        t1.start();
        Thread.sleep(40);
        ThreadTrans.flag=false;
        t2.start();
    }
}
