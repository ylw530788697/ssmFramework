package cn.evan.ssm.study.javaApiDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class GenericClassDemo {
    public static void main(String[] args) {
       /* MyGenericClass<String> myGenericClass=new MyGenericClass<String>();
        myGenericClass.setT("hdhdhdhd");
        System.out.println(myGenericClass.getT());
        show("sssss");*/
        System.out.println(new Date());

        Collection<Integer> listInteger=new ArrayList<Integer>(6);
        Collection<String> listString=new ArrayList<String>(6);
        Collection<Number> listNumber=new ArrayList<Number>(6);
        Collection<Object> listObject=new ArrayList<Object>(6);
    }

    public static void getE(Collection<? extends Number> collection){}
    public static void getS(Collection<? super Number> collection){}

    public static <T> void show(T t){
        System.out.println(t);
    }
}
