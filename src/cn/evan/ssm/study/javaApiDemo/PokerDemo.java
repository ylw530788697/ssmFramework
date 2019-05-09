package cn.evan.ssm.study.javaApiDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class PokerDemo {
    public static void main(String[] args) {
        //1 先准备好牌
        ArrayList<String> pokerBox = new ArrayList<>();
        ArrayList<String> pokerColer = new ArrayList<>();
        ArrayList<String> pokerNumber = new ArrayList<>();
        //1.4 分别给花色 以及 数字集合添加元素
        pokerColer.add("♥");
        pokerColer.add("♦");
        pokerColer.add("♠");
        pokerColer.add("♣");
        for (int i = 2; i < 11; i++) {
            pokerNumber.add(i + "");
        }
        pokerNumber.add("A");
        pokerNumber.add("J");
        pokerNumber.add("Q");
        pokerNumber.add("K");
        pokerColer.forEach(coler -> {
            pokerNumber.forEach(number -> {
                pokerBox.add(number+coler);
            });
        });
        //添加大小王
        pokerBox.add("小王");
        pokerBox.add("大王");
        System.out.println("pokerBox:"+pokerBox.toString());

    //    洗牌
        Collections.shuffle(pokerBox);

    //    发牌
        ArrayList<String> play1 = new ArrayList<>();
        ArrayList<String> play2 = new ArrayList<>();
        ArrayList<String> play3 = new ArrayList<>();
        ArrayList<String> diPai = new ArrayList<>();
        for (int i = 0; i < pokerBox.size(); i++) {
            if(i>50){
                diPai.add(pokerBox.get(i));
            }else{
                if (i%3==0){
                    play1.add(pokerBox.get(i));
                }else if(i%3==1){
                    play2.add(pokerBox.get(i));
                }else if(i%3==2){
                    play3.add(pokerBox.get(i));
                }
            }
        }
        Collections.sort(play1);
        Collections.sort(play2);
        Collections.sort(play3);
        Collections.sort(diPai);
        System.out.println("play1:"+play1.toString());
        System.out.println("play2:"+play2.toString());
        System.out.println("play3:"+play3.toString());
        System.out.println("dipai:"+diPai.toString());


    }
}
