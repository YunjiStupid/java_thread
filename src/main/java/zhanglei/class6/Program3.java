package zhanglei.class6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现一个竞拍抢答程序：要求设置三个强大者（三个线程），而后同时发出抢答指令，
 * 抢答成功者给出成功提示，未抢答成功者给出失败提示
 *
 * @author zl
 * @date 2018/9/25
 */
class MyThread implements Callable<String>{
    private boolean flag = false;

    @Override
    public String call() {
        synchronized (this){
            if(this.flag == false){
                this.flag = true;
                return Thread.currentThread().getName() + "抢答成功";
            }else {
                return Thread.currentThread().getName() + "抢答失败";
            }
        }
    }
}
public class Program3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread mt = new MyThread();
        FutureTask<String> task1 = new FutureTask<>(mt);
        FutureTask<String> task2 = new FutureTask<>(mt);
        FutureTask<String> task3 = new FutureTask<>(mt);

        new Thread(task1,"竞赛者A").start();
        new Thread(task2,"竞赛者B").start();
        new Thread(task3,"竞赛者C").start();
        System.out.println(task3.get());
        System.out.println(task2.get());
        System.out.println(task1.get());
    }
}
