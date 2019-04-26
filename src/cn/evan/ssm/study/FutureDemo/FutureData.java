package cn.evan.ssm.study.FutureDemo;

public class FutureData implements  Data {
    public volatile  static  boolean ISFLAG=false;
    private RealData realData;

    @Override
    public synchronized String getRequest() {
        while (!ISFLAG){
            try {
                wait();
            }catch (Exception e){

            }
        }
        return realData.getRequest();
    }

    public synchronized void setRealData(RealData realData){
        //如果已经获取到结果 直接返回
        if (ISFLAG){
            return;
        }
        //如果没有获取到数据，传递真是对象
        this.realData=realData;
        ISFLAG=true;
        //进行通知
        notify();
    }
}
