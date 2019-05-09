package cn.evan.ssm.study.javaApiDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<String>  list=new ArrayList<String>(5);
        list.add("sba");
        list.add("aba");
        list.add("cba");
        list.add("nba");
        Collections.sort(list);
        System.out.println(list);
    }
}
