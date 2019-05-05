package cn.evan.ssm.study;

public class StringTest
{
    public static void main(String[] args) {
        //自动装箱
        Integer total=99;
        //自动拆箱
        int totalPrim=total;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello word!");
            }
        }).start();

        //lambda表达式
        new Thread(()-> System.out.println("hello word lambda")).start();
    }
}
