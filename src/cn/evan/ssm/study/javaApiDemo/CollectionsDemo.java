package cn.evan.ssm.study.javaApiDemo;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);

        Collections.addAll(list,22,23,24,25);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
