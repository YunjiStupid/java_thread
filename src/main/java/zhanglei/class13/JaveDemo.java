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
 * 课时55 二叉树结构简介
 *      在进行链表结构开发的过程中会发现，所有的数据按照收尾相连的状态进行保存
 *      如果要想实现一棵树结构的定义，那就要考虑数据的存储结构，在二叉树的实现中其
 * 最基本的实现原理，去第一个数据为保存的根节点，小于等于根节点的数据，放在左子树，大于节点的数据，放在右子树
 *      如果进行数据检索，此时就需要进行每个节点的判断，但是它的判断是区分左右，所以不会全局判断处理，那么它的时间复杂度就是O(logn)
 *      而对于二叉树而言，在进行数据获取的时候也有三种形式：
 *          ·前序遍历（根-左-右）
 *          ·中序遍历（左-根-右）
 *          ·后序遍历（左-右-根）
 *
 * 课时56 二叉树基础实现
 *      在陕西炼二叉树的处理之中，最关键的是数据的保存，而且数据由于牵扯到对象比较问题，所以需要比较器的支持，而这个比较器首选的一定是Comparable，
 * 所以本次将保存一个Person类数据.
 *      二叉树之中的数据删除操作是非常复杂的，因为在进行数据删除的时候需要考虑的情况是比较多的
 *          情况1.如果待删除的节点没有子节点，则可以直接删。
 *          情况2.如果待删除节点只有一个子节点，那么直接删掉，并用其子节点去顶替它
 *              (1)只有一个左子树
 *              (2)只有一个右子树
 *          情况3.如果删除中间部分的节点，那么把右子树的最左边下面的节点代替至删除节点处
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
    /*public static void main(String[] args) {
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
    }*/

    /**
     * 课时56 二叉树基础实现****************************
     */
    public static void main(String[] args) {
        BinaryTree<Person> tree = new BinaryTree<Person>();
        tree.add(new Person("东经1号",16));
        tree.add(new Person("东经2号",20));
        tree.add(new Person("东经3号",36));
        tree.add(new Person("东经4号",8));
        System.out.println(Arrays.toString(tree.toArray()));
    }
}
/**
 * 比较器
 */
/*interface Comparable<T>{

    *//**
     * 实现对象的比较处理操作
     * @param o 要比较的对象
     * @return 当前数据比传入的对象小，返回负数；若大于返回正数，若等于返回0
     *//*
    public int compareTo(T o);
}*/
class Person implements Comparable<Person> {
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

    @Override
    public int compareTo(Person person) {
        return this.age - person.age;
    }

}

class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge()-p2.getAge();
    }
}
/**
 * 实现二叉树操作
 *
 * @author zl
 * @date 2018/10/9
 * @param <T> 要进行二叉树的实现
 */
class BinaryTree<T extends Comparable<T>>{

    /**
     * 保存的是根节点
     */
    private Node root;

    /**
     * 保存数据的个数
     */
    private int count;

    /**
     * 返回的数组数据
     */
    private Object[] returnData;

    //脚标控制
    private int foot = 0;

    public void add(Comparable<T> data){
        if(data == null){
            throw new NullPointerException("保存的数据不允许为空");
        }
        //所有的数据本身不具备节点关系的匹配，那么一定要保存在Node类中
        //保存节点
        Node newNode = new Node(data);
        //判断是否存在根节点，若没有，则第一个节点为根节点
        if(this.root == null){
            this.root = newNode;
        //需要为其保存到一个合适的节点位置
        }else{
            //交由Node类负责处理
            this.root.addNode(newNode);
        }
        //计数添加的数据个数
        this.count++;
    }

    /**
     * 以对象数组的形式返回全部数据，如果没有数据返回null
     */
    public Object[] toArray(){
        if(this.count == 0){
            return null;
        }
        this.returnData = new Object[this.count];
        //脚标清零
        this.foot = 0;
        //直接通过Node类负责
        this.root.toArrayNode();
        return this.returnData;
    }

    private class Node{
        //存放Comparable，可以比较大小
        private Comparable<T> data;
        //保存父节点
        private Node parent;
        //保存左子树
        private Node left;
        //保存右子树
        private Node right;

        public Node(Comparable<T> data){
            this.data = data;
        }

        /**
         * 实现节点数据的适当位置存储
         * @param newNode 创建的新节点数据
         */
        public void addNode(Node newNode){
            //比当前节点数据小
            if(newNode.data.compareTo((T)this.data) <= 0){
                //若左子树为空，保存至左子树
                if(this.left == null){
                    this.left = newNode;
                    newNode.parent = this;
                    //若不为空，则继续判断接下来的左子树
                }else{
                    this.left.addNode(newNode);
                }
                //比根节点数据大
            }else{
                if(this.right == null){
                    this.right = newNode;
                    newNode.parent = this;
                }else{
                    //继续向下判断
                    this.right.addNode(newNode);
                }
            }
        }

        //实现所有数据的获取处理（中序遍历）
        public void toArrayNode(){
            //有左子树
            if(this.left != null){
                this.left.toArrayNode();
            }
            BinaryTree.this.returnData[BinaryTree.this.foot++] = this.data;
            if(this.right != null){
                this.right.toArrayNode();
            }
        }

    }
}
