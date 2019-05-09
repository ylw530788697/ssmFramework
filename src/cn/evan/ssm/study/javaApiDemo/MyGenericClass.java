package cn.evan.ssm.study.javaApiDemo;

public class MyGenericClass<T> {
    private  T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
