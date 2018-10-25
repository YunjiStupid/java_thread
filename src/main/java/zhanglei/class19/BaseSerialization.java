package zhanglei.class19;

import java.io.*;

/**
 * 课时88 对象序列化基本概念
 *      序列化的基本定义： 所谓的对象序列化指的是 将内存中保村的对象以二进制数据流的形式进行处理，可以实现对象的保存或者网络的传输。
 * 然而并不是所有的对象都可以被序列化，在JAVA里有一个强制要求，如果对象要序列化，必须实现java.io.Serialization父接口，作为序列化的标记
 * 这个接口没有任何的方法，因为它描述的是一种累的能力.
 *      此时Person类的每个对象都可以实现二进制的数据传输，属于可以被序列化的程序类
 *
 * 课时89 序列化与反序列化处理
 *      实现序列化与反序列化的操作就可以利用以下两个类完成：
 *          ·序列化：ObjectOutputStream
 *          ·反序列化：ObjectInputStream
 *
 *
 * 课时90 transient关键字
 *      当执行了对象序列化的时候，会将类中的全部属性内容进行全部的序列化，但在某些情况下，某些属性并不需要进行序列化的处理，这个时候就可以在
 * 属性定义上使用transient关键字来完成。在进行序列化处理的时候，name属性的内容是不会被保存下来的，换而言之，读取到的内容为空“null”
 *
 *
 *
 *
 *
 *
 */
public class BaseSerialization {

    private static final File SAVE_FILE = new File("D:" + File.separator + "qwqw.person");

    public static void main(String[] args) throws Exception {
        //saveObject(new Person("小明","23"));
        System.out.println(loadObject());
    }

    public static void saveObject(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
        //序列化
        oos.writeObject(obj);
        oos.close();
    }

    public static Object loadObject() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
        //反序列化
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}

/**
 * Person可以被序列化
 * @author zl
 * @date 2018/10/24
 */
@SuppressWarnings("serial")
class Person implements Serializable{
    private transient String name;
    private String age;

    public Person(String name,String age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
