package cn.evan.ssm.study.javaApiDemo;

public class ThrowableDemo {
    public static void main(String[] args) {
        int[] arr={3,4,56};
        //System.out.println(arr[3]);
        int element = arrayTools.getElement(arr, 3);
        System.out.println(element);
    }
}

class arrayTools{
    public static int getElement(int[] arr,int index){
        if (index<0||index>arr.length-1){
            throw new ArrayIndexOutOfBoundsException("越界啦。。。。。。");
        }
        int i = arr[index];
        return  i;
    }
}
