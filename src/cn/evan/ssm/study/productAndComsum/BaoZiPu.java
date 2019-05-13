package cn.evan.ssm.study.productAndComsum;

public class BaoZiPu extends  Thread {
    private BaoZi baoZi;
    public BaoZiPu(String name,BaoZi baoZi){
        super(name);
        this.baoZi=baoZi;
    }

    @Override
    public void run() {
        int count=0;
        while (true){
            synchronized (baoZi){
                if (baoZi.flag==true){
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("包子铺开始做包子");
                if (count%2==0){
                    baoZi.pier="混沌皮";
                    baoZi.xianer="大葱肉";
                }else {
                    baoZi.pier="冰皮";
                    baoZi.xianer="水果";
                }
                System.out.println(baoZi.xianer+baoZi.pier+"做好了");
                baoZi.flag=true;
                baoZi.notify();

            }
        }
    }
}
