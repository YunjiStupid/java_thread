package zhanglei.class7;

/**
 *
 * 课时26  StringBuffer类
 *      ·每一个字符串常量都属于一个String类的匿名对象，并且不可更改
 *      ·String有两个常量池：静态常量池，运行时常量池
 *      ·String类实例化建议使用直接赋值的形式完成，这样可以直接将对象保存在对象池中，以方便下次重用
 *    虽然String类很好使用，但依旧有弊端：内容不允许修改
 *    StringBuffer类的内容可以修改
 *    StringBuffer 必须实例化
 *      ·构造方法：public StrignBuffer()
 *      ·数据追加：public StringBuffer append(数据类型 变量)
 *    所有的“+”在编译之后都变成了StringBuffer中的append()方法，并且String和StringBuffer可以相互转换
 *      ·String类对象变为StringBuffer可以依靠StringBuffer的构造方法
 *      ·所有的类对象都可以通过toString()方法将其变为String类型
 *
 *    实际上与StringBuffer类还有一个类似的功能类：StringBuilder类
 *    StringBuffer类中的方法属于，线程安全的，全都是用了synchronized标注
 *    StringBuilder类属于非线程安全
 *
 *
 * 课时27  CharSequence接口
 *    CharSequence是一个描述字符串结构的接口，在这个接口里一般发现有三个常用子类
 *    只要字符串就可以为CharSequence接口实例化
 *
 *
 * 课时28  AutoCloseable 接口
 *    AutoCloseable主要是用于日后资源开发的处理上，以实现资源的自动关闭(释放)
 *      ·关闭方法：public void close() throws Exception
 *    要想实现自动关闭，除了要使用AutoCloseable之外，还需要结合异常处理
 *
 *
 * 课时29  Runtime类
 *    描述的是运行时的状态，在整个JVM之中，Runtime类是唯一一个与JVM运行状态有关的类
 *    并且提供有一个该类的实例化对象
 *    由于在每一个JVM进程里只允许提供有一个Runtime类的对象，所以这个类的构造方法默认私有化了
 *    本类使用的是单例设计模式，并且单例设计模式一定会提供有一个static方法获取本类实例化对象
 *    由于本类属于单例设计模式，若想取得实例化对象，name可以依靠getRuntime()方法完成
 *      ·获取实例化对象：public static Runtime getRuntime();
 *      ·获取本机CPU的内核数： public int availableProcessors()
 *    除了以上的方法之外，在Runtime类里面还提供有以下四个重要操作
 *      ·获取最大可用内存空间：public long maxMemory()  默认配置为本机系统内存的四分之一
 *      ·获取可用内存空间：public long totalMemory()  默认配置为本机系统内存的64分之1
 *      ·获取空闲内存空间：public long freeMemory()
 *      ·手工进行GC处理：public coid gc()
 *
 *
 * 课时30  System类
 *      ·数组拷贝： public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
 *      ·获取当前的日期时间数值：public static long currentTimeMillis()
 *      ·进行垃圾回收：public static void gc()
 *
 *
 * 课时31  Cleaner类
 *    Cleaner类是jdk1.9之后提供的一个对象清理操作，其主要功能是进行finializable()方法的替代
 *    Java本身依然提供了给用户收尾的操作，每一个实例化对象，在回收之前至少给一个喘息的机会，最初实现对象收尾的
 *的是Object类中提供的finializable()
 *
 *
 * @author zl
 * @date 2018/9/26
 */
public class JavaAPIDemo {
    /**
     * 课时26*********************************************************
     */
    /*public static void main(String[] args) {
        StringBuffer str = new StringBuffer("hello");
        change(str);
        //插入
        str.insert(0,"zhanglei,");
        //范围删除
        str.delete(0,8);
        //字符串翻转
        str.reverse();
        System.out.println(str);

    }
    public static void change(StringBuffer temp) {
        temp.append(",world");

    }*/

    /**
     * 课时27*********************************************************
     */
    /*public static void main(String[] args) {
        //子类实例向父接口转型
        CharSequence str = "zhanglei";
        str.subSequence(0,1);
        System.out.println(str.subSequence(1,2));
    }*/

    /**
     * 课时28*********************************************************
     */
    /*public static void main(String[] args) {
        try(Message msg = new Message("zhaglei")){

            if(msg.open()){
                msg.send();
            }
        }catch ( Exception e){
            e.printStackTrace();
        }

    }*/

/**
 * 课时29*********************************************************
 */
    /*public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        System.out.println(run.availableProcessors());
        System.out.println("【1】最大可用内存" + run.maxMemory());
        System.out.println("【1】最大可使用内存" + run.totalMemory());
        System.out.println("【1】最大空闲内存" + run.freeMemory());
        String str = "";
        for(int x = 0; x < 90000; x ++){
            str +="hello";
        }
        System.out.println("【2】最大可用内存" + run.maxMemory());
        System.out.println("【2】最大可使用内存" + run.totalMemory());
        System.out.println("【2】最大空闲内存" + run.freeMemory());
        run.gc();
        System.out.println("【3】最大可用内存" + run.maxMemory());
        System.out.println("【3】最大可使用内存" + run.totalMemory());
        System.out.println("【3】最大空闲内存" + run.freeMemory());

    }*/

/**
 * 课时30*********************************************************
 */
    /*public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String str = "";
        for(int x = 0; x < 90000; x ++){
            str +="hello";
        }
        long end = System.currentTimeMillis();
        System.out.println("操作耗时：" + (end - start));

    }*/

}
interface IMessage extends AutoCloseable{
    public void send();
}
//实现消息发送处理机制
class Message implements IMessage{
    private String msg;
    public Message(String msg){
        this.msg = msg;
    }
    public boolean open(){
        System.out.println("获取消息发送连接资源");
        return true;
    }
    @Override
    public void send(){
        System.out.println("发送消息w*******" + msg);
    }
    @Override
    public void close() throws Exception{
        System.out.println("关闭资源");
    }

}
