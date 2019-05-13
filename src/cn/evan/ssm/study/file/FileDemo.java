package cn.evan.ssm.study.file;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        //int sum=5;
        //int sum1 = getSum(sum);
        //System.out.println(sum1);
        System.out.println(mutiply(5));
    }

    public static int getSum(int num){
        if (num==1){
            return 1;
        }
        return  num+getSum(num-1);
    }

    public static int mutiply(int sum){
        if (sum==1) {
            return 1;
        }
        return sum*mutiply(sum-1);

    }
}
