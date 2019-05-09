package cn.evan.ssm.study.javaApiDemo;

import com.alibaba.fastjson.JSONObject;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private Integer age;
    public Student(){
        super();
    }
    public Student(String name,Integer age){
        this.age=age;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

   /* public static void main(String[] args) {
        List<Student> list=new ArrayList<Student>(5);
        list.add(new Student("rise",18));
        list.add(new Student("evan",26));
        list.add(new Student("value",23));
        Collections.sort(list);
        list.forEach(x-> System.out.println(JSONObject.toJSONString(x)));

    }*/

  /*  @Override
    public int compareTo(Student o) {
        return this.age-o.age;
    }*/
}
