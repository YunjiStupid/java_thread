package zhanglei.class13;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 课时52 比较器问题引出
 *      所谓的比较器指的就是进行大小关系的确定判断，下面首先来分析一下比较器存在的意义是什么
 *      如果要进行数组操作，肯定使用java.util.Arrays的操作类完成，这个类里面提供有绝大部分的数组操作，同时提供了一种
 * 对象数组的排序支持：public static void sort(Object[] a)
 *      任意的一个类默认情况下无法使用系统内部的类实现数组排序或比较需求的，是因为没有明确的指定出如何比较的定义
 * （没有比较规则），java提供有比较器接口，可以重写实现
 *
 *
 * 课时53 Comparable比较器
 *      如果要实现现有对象的比较，肯定需要有比较器来制定比较规则，而比较的规则则通过Comparable来实现
 *      排序里面只需要有一个compareTo方法进行排序规则的定义，而后整个Java系统里面就可以为其进行排序处理了
 *
 * 课时54 Comparator比较器
 *      Comparator属于一种挽救的比较器支持，其目的主要是解决一些没有使用Comparable排序的类的对象数组排序
 *          ·基于Comparator的排序处理：public static <T> void sort(T[] a,Comparator<? super T> c)
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
 * @date 2018/10/8
 */
public class JaveDemo {
    /**
     * 课时52 比较器问题引出****************************
     */
    /*public static void main(String[] args) {
        //对象数组
        Integer data[] = new Integer[]{10,9,5,2,20};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));

        Person data1[] = new Person[]{
                new Person("张磊A",16),
                new Person("张磊B",20),
                new Person("张磊C",14)};
        Arrays.sort(data1);
        System.out.println(Arrays.toString(data1));
    }*/

    /**
     * 课时54 Comparator比较器****************************
     */
    public static void main(String[] args) {
        //对象数组
        Integer data[] = new Integer[]{10,9,5,2,20};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));

        Person data1[] = new Person[]{
                new Person("张磊A",16),
                new Person("张磊B",20),
                new Person("张磊C",14)};
        //Arrays.sort(data1,new PersonComparator());
        System.out.println(Arrays.toString(data1));
    }
}
/**
 * 比较器
 */
interface Comparable<T>{

    /**
     * 实现对象的比较处理操作
     * @param o 要比较的对象
     * @return 当前数据比传入的对象小，返回负数；若大于返回正数，若等于返回0
     */
    public int comparaTo(T o);
}
class Person /*implements java.lang.Comparable<Person> */{
    private String name;
    private int age;
    public Person(String name,int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*@Override
    public int compareTo(Person person) {
        return this.age - person.age;
    }*/
}

class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge()-p2.getAge();
    }
}
