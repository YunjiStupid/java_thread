package zhanglei.class4;

/**
 * @Author zhanglei
 * @Date 2018/9/16 16:03
 */
class Message {
    private String title;
    private String content;
    /**
     *  true 可以生产  不可消费
     *  false 可以消费 不可生产
     */
    private boolean flag = true;

    public synchronized String get() {
        if(flag){
            try{
                super.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            return this.title + this.content;
        }finally {
            this.flag = true;
            super.notify();
        }

    }

    public synchronized void set(String title, String content) {
        if(!flag){
            try{
                super.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.title = title;
        this.content = content;
        this.flag = false;
        super.notify();
    }
}

class Producer implements Runnable{
    private Message msg;
    public Producer(Message msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0){
                this.msg.set("王建","宇宙大帅哥");
            }else{
                this.msg.set("小高","猥琐第一人");
            }
            }
        }
    }
class Consumer implements Runnable{
    private Message msg;
    public Consumer(Message msg){
        this.msg = msg;
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            System.out.println(this.msg.get());
        }
    }
}
public class ThreadDemo {
    public static void main(String[] args) throws Exception{
        Message msg = new Message();
        new Thread(new Producer(msg)).start();
        new Thread(new Consumer(msg)).start();
    }
}
