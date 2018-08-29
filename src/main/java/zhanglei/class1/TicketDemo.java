package zhanglei.class1;

/**
 * @Author zhanglei
 * @Date 2018/8/27 22:23
 */

/*class MyThread implements Runnable{
    private
}*/
class MyThread implements Runnable{
    private int ticket = 5;
    public void run() {
        for(int x = 0; x < 100; x ++){
            if(this.ticket > 0)
            System.out.println("卖票，ticket = " + this.ticket--);
        }
    }
}

public class TicketDemo{
    public static void main(String [] args){
        MyThread mt = new MyThread();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
    }
}
