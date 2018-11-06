package zhanglei.class21;

import java.util.Date;

/**
 * 课时99 反射机制简介
 *      对于反射技术，先考虑“反”与“正”的概念。
 *      所谓的“正”操作指的是当我们要使用一个类的时候，一定要先导入程序所在的包
 * 而后根据 类进行对象的时候并且依靠对象调用类中的方法。
 *      所谓的“反”：根据实例化对象反推出其类型
 *      如果要想实现反的处理操作，那么首先要采用Object类中所提供的一个方法
 *          ·获取Class对象信息：public final Class<?> getClass()
 *
 * 课时100 Class类对象的三种实例化模块
 *      反射之中所有的核心操作都是通过Class类对象展开的，可以说Class类是反射操作的根源所在，但是这个类如果要想获取它的实例化对象
 * 可以采专用三种方式完成，首先来观察Class类的定义：
 *      public final class Class<T> extends Object implements Serializable,GenericDeclaration,Type,AnnotatedElement
 * 从JDK1.5开始，Class定义的时候可以使用泛型进行标记，这样的用法主要是希望可以避免向下转型。
 *      1.【Object类支持】object类可以根据实例化对象获取Class对象：
 *          ·public final Class<?> getClass()
 *          这种方式有个小缺点，若现在只是想获得Class类对象，则必须产生指定类对象后才可以获得
 *      2.【JVM直接支持】采用类.class的形式实例化
 *      3.【Class类支持】在Class类里面提供有一个static方法：
 *          ·加载类：public static Class<?> forName(String className) throws ClassNotFoundException;
 *          这种模式最大的特点是可以直接采用字符串的形式定义要使用的类型，并且程序中不需要导入包
 *
 *
 *
 *
 *
 *
 * @author zl
 * @date 2018/10/24
 */
public class ReflexDemo {

    /**
     * 课时99*******************************************
     */
    /*public static void main(String[] args) {
        Date date = new Date();
        //根据实例化对象找到对象的所属类型
        System.out.println(date.getClass());

    }*/

    /**
     * 课时100*******************************************
     */
    public static void main(String[] args) throws  Exception {
        Person person = new Person();
        //1.根据实例化对象找到对象的所属类型
        Class<? extends Person> cls = person.getClass();
        System.out.println(cls);
        //2.
        Class<? extends Person> cls2 = Person.class;
        //3.
        Class<?> cls3 = Class.forName("zhanglei.class21.Person");
        //获取的是类的完整名称
        System.out.println(cls.getName());
        System.out.println(cls2.getName());
        System.out.println(cls3.getName());


    }
}
class Person{
}
