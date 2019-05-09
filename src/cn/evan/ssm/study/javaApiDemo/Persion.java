package cn.evan.ssm.study.javaApiDemo;

import cn.evan.ssm.study.FutureDemo.Data;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;

public class Persion {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persion persion = (Persion) o;
        return age == persion.age &&
                Objects.equals(name, persion.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
      /*  Object o=new Object();
        System.out.println(o.toString());
        System.out.println(new Persion());
        System.out.println(new Persion().toString());
        boolean equals = o.equals(new Persion());
        System.out.println(equals);
        StringUtils.isNullOrEmpty("dd");*/
        //System.out.println(new Date(0L));
        //Date date=new Date();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年mm月dd日");
        //System.out.println(simpleDateFormat.format(date));
        //function();
        //long l = System.currentTimeMillis();
        arryCopy();
        String s="hello";
        s=s+"world";
        //System.out.println(s);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer aaa = stringBuffer.append("aaa");
        //stringBuffer.insert()
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(aaa);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        //hashMap.put()

    }

    public static void arryCopy(){
        //int[] old =new int[]{1,2,3,4};
        ////int[] newArray=new int[];
        //System.arraycopy(old,0,newArray,1,4);
        //System.arraycopy(old, 0, newArray, 3, 4);
        //System.out.println(JSONObject.toJSONString(newArray));
    }
    public static void function(){
        System.out.println("请输入出生日期  格式yyyy-mm-dd");
        String birthday = new Scanner(System.in).next();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(birthday);
            Date date = new Date();

            System.out.println("today:"+date);
            long time = parse.getTime();
            System.out.println("time"+time/1000/60/60/24);
            long today = date.getTime();
            System.out.println("today"+today/1000/60/60/24);
            long l = today - time;
            if (l<0){
                System.out.println("还没出生");
            }else{
                System.out.println(l/1000/60/60/24);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(birthday);
        Calendar instance = Calendar.getInstance();
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
