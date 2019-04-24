package cn.evan.ssm.study.transTickets;

public class ThreadTrans implements Runnable {
    private Integer Tickets=100;
    public Object object=new Object();

    public  static boolean flag=true;



    @Override
    public void run() {
        if (flag){
            while (Tickets > 0) {
                synchronized (this) {
                    if (Tickets > 0) {
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        System.out.println(Thread.currentThread().getName() + ",出售票" + (100 - Tickets + 1) + "票");
                        Tickets--;
                    }
                }
            }
        }else{
            while (Tickets>0){
                sale();
            }
        }

    }

    private synchronized void sale() {
        //同步代码块
        //synchronized (object) {
            if (Tickets > 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",出售票" + (100 - Tickets + 1) + "票");
                Tickets--;
            }
        //}
    }


}


