package zhanglei.class6;

/**
 * 设计一个生产电脑和搬运电脑的类，要求生产出一天电脑就搬走一台电脑，
 * 如果没有新的电脑生产，搬运工要等待新电脑生产出来，
 * 如果生产出的电脑没有搬走，则要等待电脑搬走之后再生产，并统计出生产的电脑数量
 *
 * @author zl
 * @date 2018/9/25
 */
class Producer implements Runnable{
    private ResourceComputer resourceComputer;

    public Producer(ResourceComputer resourceComputer){
        this.resourceComputer = resourceComputer;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50 ; x ++){
            try {
                this.resourceComputer.make();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private ResourceComputer resourceComputer;

    public Consumer(ResourceComputer resourceComputer){
        this.resourceComputer = resourceComputer;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50 ; x ++){
            try {
                this.resourceComputer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ResourceComputer{
    private Computer computer;
    public synchronized void make() throws InterruptedException {
        if(this.computer != null){
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100);
        this.computer = new Computer("dwdwdw",1.1);
        super.notifyAll();

    }

    public synchronized void get() throws InterruptedException {
        if(this.computer == null){
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(200);
        System.out.println(this.computer);
        this.computer = null;
        super.notifyAll();
    }
}
class Computer{
    private static int count = 0;
    private String name;
    private double price;
    public Computer(String name,double price){
        this.name = name;
        this.price = price;
        count ++;
    }

    @Override
    public String toString() {
        return "第 " + count + "台" + "Computer{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
public class Program2 {
    public static void main(String[] args) {
        ResourceComputer res = new ResourceComputer();
        new Thread(new Producer(res)).start();
        new Thread(new Consumer(res)).start();
    }
}
