package zhanglei.class1;

class MyThread implements Runnable{
    private String title;
    public MyThread(String title){
        this.title = title;
    }

    public void run() {
        for(int x = 0; x < 10; x++){
            System.out.println(this.title + " 运行，x =  "+ x);
        }
    }
}

public class ThreadDemo {

    public static void main(String [] args ){
        Thread m1t = new Thread(new MyThread("线程 1"));
        Thread m2t = new Thread(new MyThread("线程 2"));
        Thread m3t = new Thread(new MyThread("线程 3"));
        m1t.start();
        m2t.start();
        m3t.start();
    }

    /**
     * Thread与Runnable关系
     * Thread类也是Runnable类的子类
     */

}
