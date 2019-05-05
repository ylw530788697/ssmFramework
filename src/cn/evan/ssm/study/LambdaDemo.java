package cn.evan.ssm.study;

import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {
    @Test
    public void oldRunable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("the old runable now is using!");
            }
        }).start();
    }

    @Test
    public void runable(){
        new Thread(()-> System.out.println("lambda is runing")).start();
    }
    @Test
    //使用lambda表达式对集合进行迭代
    public void iterTest(){
        List<String> languages=Arrays.asList("java","scala","python");
       /* for (String each:languages) {
            System.out.println(each);
        }*/

        languages.forEach(x-> System.out.println(x));

        languages.forEach(System.out::println);
    }

    @Test
    //3 用lambda表达式实现map
    public void mapTest(){
        List<Integer> integerList = Arrays.asList(10, 12, 30);
        integerList.stream().map(x->x+x*0.05).forEach(x-> System.out.println(x));
    }
    @Test
    //用lambda表达式实现map与reduce
    public void mapReduceTest(){
        List<Integer> integerList=Arrays.asList(10,20,30);
        Integer result=integerList.stream().map(x->x*2).reduce((sum, x)->sum+x).get();
        System.out.println(result);
    }
    @Test
    public void filterTest(){
        List<Integer> integerList=Arrays.asList(10,20,30);
        Stream<Integer> stream = integerList.stream();
        List<Integer> collect = integerList.stream().filter(x -> x > 20).collect(Collectors.toList());
        collect.forEach(x-> System.out.println(x));
    }

    public static void filterTest(List<String> list, Predicate<String> predicate){
        list.stream().filter(x->predicate.test(x)).forEach(x-> System.out.println(x+""));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Python","scala","Shell","R");
        System.out.println("list start with j:");
        filterTest(list,x->x.startsWith("J"));
    }
}
