package cn.evan.ssm.study.ThreadSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Res {
    public String userName;
    public String sex;
    public boolean flag = false;
    Lock lock=new ReentrantLock();
}

class out extends Thread {
    Res res;

    public out(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        new ThreadLocal<>().set(300);
        int count = 0;
        while (true) {
            synchronized (res) {
                if (res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    res.userName = "小红";
                    res.sex = "女";
                } else {
                    res.userName = "和呵呵小军";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
                res.flag = true;
                res.notify();
            }

        }
    }
}

class Input extends Thread {
    Res res;

    public Input(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        Vector<Object> vector = new Vector<>();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("");
        vector.add("");
        Map<Object,String> map=new HashMap<>();
        map.put("aa","aa");
        Hashtable<String, String> stringStringHashtable = new Hashtable<>();
        stringStringHashtable.put("aa","bb");
        while (true) {
            synchronized (res) {
                if (res.flag) {
                    System.out.println(res.userName + "," + res.sex);
                    res.flag = false;
                    res.notify();
                } else {
                    try {
                        //让当前线程 从运行状态变为休眠状态 并且释放锁的资源
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

public class OutInputThread {
    public static void main(String[] args) {
        Res res = new Res();
        out out = new out(res);
        Input input = new Input(res);
        out.start();
        input.start();
    }
}
