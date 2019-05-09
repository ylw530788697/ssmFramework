package cn.evan.ssm.study.javaApiDemo;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("cc");
        arrayList.add("dd");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
