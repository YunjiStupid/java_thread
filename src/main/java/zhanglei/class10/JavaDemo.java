package zhanglei.class10;

/**
 * 课时38 认识正则表达式
 *    如果要想进行正则的处理操作，那就要对常用的正则标记进行掌握，在java中提供有pattern包，提供所有的正则标记
 *      1、【数量：单个】字符匹配
 *          ·任意字符：表示由任意字符组成
 *          ·\\：匹配“\“
 *          ·\n：匹配换行
 *          ·\t：匹配制表符
 *      2、【数量：单个】字符集（可以从里面任选一个字符）
 *          ·[abc]：表示可能是字母abc中的任意一个
 *          ·[^abc]：表示不是字母abc中的任意一个
 *          ·[a-zA-Z]：表示由一个任意字母组成，不区分大小写
 *          ·[0-9]：表示由一个数字组成
 *      3、【数量：单个】简化字符集
 *          ·.：表示任意一个字符
 *          ·\d：等价于"[0-9]"范围
 *          ·\D：等价于"[^0-9]"范围
 *          ·\s：匹配任意的一位空格，可能是空格、换行、制表符
 *          ·\S：匹配任意的非空格数据
 *          ·\w：匹配字母、数字。下划线，等价于“[a-zA-Z_0-9]”
 *          ·\W：匹配非字母、数字。下划线，等价于“[^a-zA-Z_0-9]”
 *      4、边界匹配：
 *          ·^：匹配边界开始
 *          ·$：匹配边界结束
 *      5、数量表达，默认情况下只有添加了数量单位才可以匹配多位字符
 *          ·表达式？：该正则可以出现0次或者1次
 *          ·表达式*：该正则可以出现0次、1次或多次
 *          ·表达式+：该正则可以出现1次或多次
 *          ·表达式{n}：表达式的长度正好为n次
 *          ·表达式{n,}：表达式的长度为n次以上
 *          ·表达式{n,m}：表达式的长度为n~m次
 *      6、逻辑表达式
 *          ·表达式X表达式Y：X表达式之后紧跟上Y表达式
 *          ·表达式X|表达式Y：有一个表达式满足即可
 *          ·(表达式)：为表达式设置一个整体描述，可以为整体描述设置数量单位
 *
 *
 * 课时40 String类对正则的支持
 *          ·public boolean matches(String regex)    普通  将指定字符串进行正则判断
 *          ·public String replaceAll(String regex,String replacement)    普通  替换全部
 *          ·public String replaceFirst(String regex,String replacement)    普通  替换首个
 *          ·public String[] split(String regex)    普通  正则拆分
 *          ·public String[] split(String regex，int limit)    普通  正则拆分
 *
 *
 *
 * @author zl
 * @date 2018/9/29
 */
public class JavaDemo {
    /**
     * 课时38****************************
     */
    /*public static void main(String[] args) {
        String str = "xsd";
        String regex = "\\w{3}";
        System.out.println(str.matches(regex));
    }*/

    /**
     * 课时40*****************************
     */
    public static void main(String[] args) {
        //实现字符串替换（删除掉非字母与数字）
        String str = "xsd&^$##%^*)+_}{:LKUBVRRFHIJUGGyfg6wd7whdwidn+-*/*";
        String regex = "[^a-zA-Z0-9]+";
        System.out.println(str.replaceAll(regex,""));
        System.out.println("********************************");

        //实现字符串的拆分
        String str1 = "xsd&^$77#1%7^17*)1_}71{:L1KU47BVRRF58HIJUGGyf57g6wd72whdwi7dn+-*/*";
        String regex1 = "\\d+";
        String result [] = str1.split(regex1);
        for(int x = 0; x < result.length; x++){
            System.out.println(result[x] + "、");
        }
        System.out.println("********************************");

        //判断一个数据是否为小数
        String str2 = "100.#";
        String regex2 = "\\d+(\\.\\d+)?";
        System.out.println(str2.matches(regex2));
        System.out.println("********************************");
    }
}
