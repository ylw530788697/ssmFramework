package cn.evan.ssm.study.designMode.danLi;
//懒汉式 类初始化时不会创建对象 真正需要的时候才会创建 天生线程不安全  需要解决线程安全问题
public class LanHan {
    private static LanHan lanHan;
    private LanHan(){
    }

    public static synchronized  LanHan getInstance(){
        if (lanHan==null){
            lanHan=new LanHan();
        }
        return lanHan;
    }

    public static void main(String[] args) {
        LanHan instance = LanHan.getInstance();
        LanHan instance1 = LanHan.getInstance();
        System.out.println(instance.equals(instance1));
    }
}
