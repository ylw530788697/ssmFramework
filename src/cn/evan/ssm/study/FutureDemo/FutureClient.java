package cn.evan.ssm.study.FutureDemo;

public class FutureClient {
    public Data submit(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData=new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return  futureData;
    }

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();

        Data request=futureClient.submit("请求参数");
        String request1 = request.getRequest();
        System.out.println("请求参数发送成功");
        System.out.println("执行其他任务");

        System.out.println("获取到结果是。。。。"+request1);
    }
}


