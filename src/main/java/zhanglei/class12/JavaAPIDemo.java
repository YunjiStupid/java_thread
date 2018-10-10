package zhanglei.class12;

import java.util.Base64;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * 课时48 Optional类
 *      Optional类是进行null的相关处理，在以前的程序开发中，如果为了防止程序之中出现空指针异常
 * 往往可以追加有null的验证.
 *      在引用接受的一方往往都是被动的进行判断，Optional类可以实现null的处理操作，
 * 在这个类中提供有如下的一些操作：
 *          ·返回空数据：public static <T> Optional<T> empty();
 *          ·获取数据：public T get();
 *          ·保存数据，但是不允许出现null：public static <T> Optional<T> of(T value);
 *              |-如果在保存数据的时候存在null，则会抛出NUllPointerException异常
 *          ·保存数据，允许为空： public static <T> Optional<T> ofNullabel(T value);
 *          ·空的时候，返回其他数据：public T orElse(T other);
 *
 * 课时49 ThreadLocal类
 *      在真正去了解ThreadLocal类作用的时候，编写一个简单程序做一个分析
 *      在保持Channel(所有发送的通道)核心结构不变的情况下，需要考虑每个线程的消息独立性，发现对于
 * Channel类而言除了要保留有发送的消息之外，还应该多存放有一个个每个线程的标记，可以通过ThreadLocal类
 * 来存放数据。在ThreadLocal类里面提供有如下的操作方法：
 *          ·构造方法：public ThreadLocal();
 *          ·设置数据：public void set(T value);
 *          ·获取数据：public T get();
 *          ·删除数据：public void remove();
 *      ThreadLocal一般会存两个对象（1.当前线程对象，2.线程数据对象），其中线程对象无法看见
 *
 * 课时50 定时调度
 *      定时器的主要操作是进行定时任务的处理，这种任务的处理只是实现了间隔触发的操作
 *      定时任务的处理操作主要需要有一个定时操作的主体类，以及一个定时任务的控制，可以使用两个类实现
 *      每一个任务都是独立的线程
 *          ·java.util.TimeTask类：实现定时任务处理
 *          ·java.util.Timer类：进行任务的启动
 *              |-任务启动：public void schedule(TimeTask task,long delay),延迟单位为毫秒
 *              |-间隔触发：timer.scheduleAtFixedRate(new MyTask(),500,1000),延迟500毫秒开始。间隔1000毫秒触发一次
 *
 * 课时51 Base64加密与解密
 *      新的加密处理操作类，Base64，在这个类里面有两个内部类
 *          ·Base64.Encoder：进行加密处理
 *              |-加密处理：public byte[] encode(byte[] src)
 *          ·Base64.Decoder：进行解密处理
 *              |-解密处理：public byte[] decode(String src)
 *      Base64可以实现加密解密操作，但是这是个公版算法，不安全，最好的做法是使用盐值操作
 *
 *
 *
 *
 *
 * @author zl
 * @date 2018/10/8
 */
public class JavaAPIDemo {
    /**
     * 课时48*******************************
     */
    /*public static void main(String[] args) {
        IMessage temp = MessageUtil.getMessage().orElse(new MessageImpl());
        MessageUtil.useMessage(temp);
    }*/

    /**
     * 课时49*******************************
     */
    /*public static void main(String[] args) {
        new Thread(()->{
            //实例化消息主题对象
            Message msg = new Message();
            //设置消息内容
            msg.setInfo("第一个线程的消息信息");
            //设置要发送的消息
            Channel.setMessage(msg);
            //发送消息
            Channel.send();
        },"消息发送者A").start();
        new Thread(()->{
            //实例化消息主题对象
            Message msg = new Message();
            //设置消息内容
            msg.setInfo("第二个线程的消息信息");
            //设置要发送的消息
            Channel.setMessage(msg);
            //发送消息
            Channel.send();
        },"消息发送者B").start();
        new Thread(()->{
            //实例化消息主题对象
            Message msg = new Message();
            //设置消息内容
            msg.setInfo("第三个线程的消息信息");
            //设置要发送的消息
            Channel.setMessage(msg);
            //发送消息
            Channel.send();
        },"消息发送者C").start();

    }*/

    /**
     * 课时50*******************************
     */
    /*public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new MyTask(),5);
        //定义间隔任务，500毫秒后执行，每隔1秒执行一次
        timer.scheduleAtFixedRate(new MyTask(),500,1000);
    }*/

    /**
     * 课时51*******************************
     */
    public static void main(String[] args) {
        //要发送的信息内容
        String msg = "zhanglei";
        //数据加密
        String encMsg = new String(Base64.getEncoder().encode(msg.getBytes()));
        System.out.println(encMsg);
        String oldMsg = new String(Base64.getDecoder().decode(encMsg));
        System.out.println(oldMsg);
    }
}
class MyTask extends TimerTask{
    @Override
    public void run() {//多线程处理方法
        System.out.println(Thread.currentThread().getName() + "、定时任务执行，当前时间" + System.currentTimeMillis());
    }
}
class Message{
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
//消息的发送通道
class Channel{
    private static final ThreadLocal<Message> THREADLOCAL = new ThreadLocal<>();
    public static void setMessage(Message m){
        //向THREADLOCAL保存数据
        THREADLOCAL.set(m);
    }
    public static void send(){
        System.out.println(Thread.currentThread().getName() + "消息发送：" + THREADLOCAL.get());
    }
}
class MessageUtil{
    private MessageUtil(){}
    public static Optional<IMessage> getMessage(){
        return Optional.of(/*new MessageImpl()*/null);
    }
    public static void useMessage(IMessage msg){
        //有可能因为出现null,出现空指针异常
        if(msg != null){
            System.out.println(msg.getContent());
        }

    }
}
interface IMessage{
    public String getContent();
}
class MessageImpl implements IMessage{
    @Override
    public String getContent() {
        return "zhanglei";
    }
}
