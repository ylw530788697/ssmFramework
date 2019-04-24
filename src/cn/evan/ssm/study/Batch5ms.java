package cn.evan.ssm.study;

import java.util.ArrayList;
import java.util.List;

public class Batch5ms {
    public static void main(String[] args) {
    //    初始化数据
        List<UserModel> list = initUser();
        //    定义每个线程分批发送大小

    //    计算每个线程需要废品跑的线程
    //    分配发送
    }

    public static List<UserModel>  initUser(){
        List<UserModel> list=new ArrayList<UserModel>();
        for (int i=1;i<10;i++){
            list.add(new UserModel("userId"+i,"userName"+i));
        }
        return  list;
    }
}
