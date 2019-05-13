package cn.evan.ssm.study.productAndComsum;

public class Demo {
    public static void main(String[] args) {
        BaoZi baoZi=new BaoZi();
        BaoZiPu baoZiPu=new BaoZiPu("包子铺",baoZi);
        ChiHuo chiHuo=new ChiHuo("吃货",baoZi);
        baoZiPu.start();
        chiHuo.start();
    }
}
