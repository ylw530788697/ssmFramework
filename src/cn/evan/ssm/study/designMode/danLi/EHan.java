package cn.evan.ssm.study.designMode.danLi;

//饿汉式
public class EHan {
    //类初始化的时候就会创建对象，天生安全 调用效率高。如果不使用对象的时候 会浪费内存
    private static final EHan singleDemo=new EHan();
    private EHan() {
    }
    //会有线程安全问题
    public static EHan getInstance(){
        return  singleDemo;
    }
    public static void main(String[] args) {
        EHan instance = EHan.getInstance();
        EHan instance1 = EHan.getInstance();
        System.out.println(instance.equals(instance1));
    }
}
