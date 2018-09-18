package zhanglei.class3;

/**
 *
 * @author zl
 * @date 2018/9/13
 */
class WangJian{
    public synchronized  void say(XiaoQiang xq){
        System.out.println("给我10块钱啊");
    }
    public synchronized void get(){
        System.out.println("让路");
    }
}
class XiaoQiang{
    public synchronized  void say(WangJian wj){
        System.out.println("让我先跑路啊");
    }
    public synchronized void get(){
        System.out.println("跑路");
    }
}
public class DeadLock implements Runnable {

    private XiaoQiang xq = new XiaoQiang();
    private WangJian wj = new WangJian();

    public void run() {
        wj.say(xq);
    }
    public DeadLock(){
        new Thread(this).start();
        xq.say(wj);
    }
    public static void main(String [] args){
        new DeadLock();
    }
}
