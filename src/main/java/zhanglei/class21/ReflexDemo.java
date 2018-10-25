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
 *
 *
 *
 *
 *
 *
 *
 *
 *
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

    public static void main(String[] args) {
        Date date = new Date();
        //根据实例化对象找到对象的所属类型
        System.out.println(date.getClass());

    }
}
