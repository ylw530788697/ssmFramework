package cn.evan.ssm.study.javaApiDemo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
  /*      Map<String,String> map=new Hashtable<>(5);
        map.put("黄晓明","杨颖");
        map.put("文章","马伊琍");
        map.put("邓超","孙俪");
        System.out.println(map);

        map.keySet().forEach(x->{
            System.out.println(map.get(x));
        });

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry entry:entries
             ) {
            System.out.println("entrySet:"+entry.getKey());
            System.out.println("entrySet:"+entry.getValue());
        }*/

      /*  map.entrySet().forEach(x->{
            System.out.println("entrySet:"+x.getKey());
            System.out.println("entrySet:"+x.getValue());
        });*/

        Map<Student, String> map = new HashMap<Student, String>(5);
        Student ceshi = new Student("ceshi",24);
        map.put(ceshi,"广州市天河区");
        map.put(new Student("zhangsan", 24), "深圳市南山区");
        map.put(new Student("lisi", 25), "深圳市宝安区");
        map.put(ceshi,"香港九龙");
        map.keySet().forEach(key->{
            System.out.println(map.get(key));
        });
    }

}
