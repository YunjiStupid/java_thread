package zhanglei.class6;

/**
 * 设计四个线程对象，两个线程执行减操作，两个线程执行加操作
 *
 * @author zl
 * @date 2018/9/25
 */
/**
 * 操作的资源类
 */
class Resource{
    private int num = 0;
    //true:进行加法操作 false 进行减法操作
    private boolean flag = true;

    public synchronized void add() throws InterruptedException {
        //现在需要执行的是减法操作，加法操作要等待
        if(this.flag == false){
            super.wait();
        }
        Thread.sleep(100);
        this.num ++;
        System.out.println("加法操作," + Thread.currentThread().getName() + "num = " + this.num);
        this.flag = false;
        super.notifyAll();
    }

    public synchronized void sub() throws InterruptedException {
        //现在需要执行的是加法操作，减法操作要等待
        if(this.flag == true){
            super.wait();
        }
        Thread.sleep(200);
        this.num --;
        System.out.println("减法操作," + Thread.currentThread().getName() + "num = " + this.num);
        this.flag = true;
        super.notifyAll();
    }
}
class AddThread implements Runnable{
    private Resource resource;

    public AddThread(Resource resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50; x++){
            try {
                this.resource.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class SubThread implements Runnable{
    private Resource resource;

    public SubThread(Resource resource){
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50; x++){
            try {
                this.resource.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Program1 {
    public static void main(String[] args) throws Exception {
        Resource res = new Resource();
        AddThread at = new AddThread(res);
        SubThread st = new SubThread(res);
        new Thread(at,"加法线程 - A").start();
        new Thread(at,"加法线程 - B").start();

        new Thread(st,"减法线程 - C").start();
        new Thread(st,"减法线程 - D").start();
    }
}
