package cn.evan.ssm.study.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FeatureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> submit = pool.submit(new Callable<String>() {
            @Override
            public String call() {
                return null;
            }
        });

        System.out.println("1:主线程开始执行");
        //获取执行结果
        String result = submit.get();
        System.out.println("2:result:"+result);

    }
}
