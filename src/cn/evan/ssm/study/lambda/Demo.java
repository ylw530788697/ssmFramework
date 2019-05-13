package cn.evan.ssm.study.lambda;

public class Demo {
    public static void main(String[] args) {
        InvokeCook.invokeCooke(new Cook() {
            @Override
            public void makeFood() {
                
            }
        });
    }
}
