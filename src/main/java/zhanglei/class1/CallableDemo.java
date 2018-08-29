package zhanglei.class1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author zhanglei
 * @Date 2018/8/29 22:50
 */
class MyThreadCa implements Callable<String>{
    public String call() {
        for(int x = 0; x < 10; x++){
            System.out.println("*********,线程执行 x = " + x);
        }
        return "线程执行完毕";
    }
}
public class CallableDemo {
    public static void main(String [] args) throws Exception{
        FutureTask<String> task = new FutureTask<String>(new MyThreadCa());
        new Thread(task).start();
        System.out.println(task.get());
    }
}
