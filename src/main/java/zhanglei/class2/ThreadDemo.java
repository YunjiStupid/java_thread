package zhanglei.class2;

/**
 * @Author zhanglei
 * @Date 2018/9/9 10:07
 */
class MyThread implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
public class ThreadDemo {
    public static void main(String args[]){
        /*MyThread mt = new MyThread();
        new Thread(mt,"线程A").start();
        new Thread(mt).start();
        new Thread(mt,"线程B").start();
        mt.run();*/
        test();
    }

    public static void test(){
        MyThread mt = new MyThread();
        System.out.println("执行方法一");
        new Thread(()->{//子线程负责统计
            int temp = 0;
            for(int x = 0; x < 10; x++){
                temp++;
                System.out.println(temp);
            }
        });
        System.out.println("执行方法二");
        System.out.println("执行方法三");
    }
}
