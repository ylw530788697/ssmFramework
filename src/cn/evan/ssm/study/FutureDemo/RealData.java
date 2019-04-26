package cn.evan.ssm.study.FutureDemo;

//获取真实数据
public class RealData implements Data {
    private String result;

    public RealData(String data) {
        System.out.println("正在使用data：" + data + "网络请求数据，耗时操作需要等待");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果中......");
        result = "evan dayeeeee";
    }


    @Override
    public String getRequest() {
        return result;
    }
}
