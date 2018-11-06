package zhanglei.class22;

/**
 * 课时101 反射实例化对象
 *      获取Class对象之后最大的意义实际上并不是在于只是一个对象的实例化操作形式，更重要的是Class类中提供有一个对象的反射实例化对象方法(代替了关键字new)
 *          ·在JDK1.9以前的实例化：public T newInstance() throws ...
 *          ·JDK1.9之后：clazz.getDeclaredConstructor().newInstance();
 *      现在通过反射实现的对象实例化处理，依然要调用类中的无参构造方法，其本质等于创建了该类的对象
 *      因为默认的Class类中的newInstance()方法只能够调用无惨构造，所以很多开发者认为，其描述的不准确，故将其变化了形式
 *
 * 课时102 反射与工厂设计模式
 *      要想进行对象的实例化处理，除了使用关键字new，还可以使用还可以使用反射机制来完成，但是：
 * （1）为什么要提供有一个反射的实例化?（2）到底使用new还是使用反射
 *      工厂设计模式的最大特点：客户端的程序类不直接牵扯到对象的实例化管理，只与接口发生关联，通过工厂类获取指定接口的实例化对象
 *      工厂设计模式最有效解决的是子类与客户端的耦合问题，但是解决的核心思想是在于提供有一个工厂类作为过渡段
 *      那么这个时候，最好的解决方案就是不使用关键字new，因为关键字new在使用的时候需要一个明确的类存在，而newInstance的方法，只需要有一个明确标识类名称的字符串
 * 即可应用
 *
 * 课时103 反射与单例设计模式
 *      单例设计模式的核心在于：类内部构造方法私有化，在类的内部产生实例化对象之后，通过static方法获取实例化对象进行类中的结构调用，单例设计模式一共有两类：懒汉式和饿汉式
 * 饿汉式不在本次讨论范围之内，主要讨论懒汉式
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class RexExceple {

    /**
     * 课时101 反射实例化对象************************************
     */
    /*public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("zhanglei.class22.Person");
        Object obj = cls.newInstance();
        System.out.println(obj);
    }*/

    /**
     * 课时102**************************************
     */
    /*public static void main(String[] args) {
        //如果直接实例化，一定会有偶合问题
        IMessage msg = Factory.getInstance("zhanglei.class22.NetMessage22",IMessage.class);
        msg.send();
    }*/
    public static void main(String[] args) {
        for(int x = 0; x < 3; x ++){
            new Thread(()->{
                Singleton sinA = Singleton.getInstance();
                sinA.print();
            },"单例消费端-" + x).start();
        }

    }
}

interface IMessage{
    //消息发送
    public void send();
}

class NetMessage implements IMessage{
    @Override
    public void send() {
        System.out.println("消息发送**********");
    }
}
class NetMessage22 implements IMessage{
    @Override
    public void send() {
        System.out.println("消息发送22**********");
    }
}


class Factory{
    private Factory(){ }
    /**
     * 获取接口实例化对象
     * @author zl
     * @date 2018/11/6
     * @param className 接口的子类
     * @param clazz 描述的是接口的类型
     * @return T 如果子类存在则返回指定接口实例化对象
     */
    public static <T>T getInstance(String className,Class<T> clazz){
        T instance = null;

        try{
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        return instance;
    }
}

class Singleton{
    private static volatile Singleton instance = null;
    private Singleton(){
        System.out.println("**************实例化");
    }
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }

        }
        return instance;
    }
    public void print(){
        System.out.println("www.mldn.com");
    }
}