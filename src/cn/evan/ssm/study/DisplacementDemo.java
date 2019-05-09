package cn.evan.ssm.study;

import javax.print.attribute.standard.PrinterInfo;

/**
 * @author Evan Yang
 * @version 1.0
 * @create date：2019-5-6
 */
public class DisplacementDemo {
    public static void main(String[] args) {
        int number=20;
        System.out.println("原始number的二进制结果是："+Integer.toBinaryString(number));
        //左移
        Integer leftOneNumber=number<<1;
        //相当于乘以2
        System.out.println("左移一位number的值是："+leftOneNumber);
        System.out.println("左移一位number的二进制结果是："+Integer.toBinaryString(leftOneNumber));
        Integer leftTwoNumber=number<<2;
        //相当于乘以4
        System.out.println("左移两位number的值是："+leftTwoNumber);
        System.out.println("左移两位number的二进制结果是："+Integer.toBinaryString(leftTwoNumber));

        //右移
        Integer rightOneNumber=number>>1;
        //相当于除以2
        System.out.println("右移一位number的值是："+rightOneNumber);
        System.out.println("右移一位number的二进制结果是："+Integer.toBinaryString(rightOneNumber));
        Integer rightTwoNumber=number>>2;
        //相当于除以4
        System.out.println("右移两位number的值是："+rightTwoNumber);
        System.out.println("右移两位number的二进制结果是："+Integer.toBinaryString(rightTwoNumber));
        System.out.println("右移两位number的二进制结果是："+Integer.toBinaryString(number>>3));
    }
}
