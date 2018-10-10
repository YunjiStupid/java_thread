package zhanglei.class14;

import java.util.Arrays;
import java.util.Random;

/**
 * 课时59 StringBuffer使用
 *      定义一个StringBuffer类对象，然后通过append()方法向对象中添加26个小写字母，要求每次只添加一次，共添加26次，然
 * 后按照逆序的顺序输出，并且可以删除前5个字符
 *
 * 课时60 随机数组
 *      利用Random类产生5个1-30之间（包括1和30）的随机整数
 *
 * 课时61 Email验证
 *      输入一个Email地址，使用正则表达式验证该Email地址是否正确
 *
 *
 * @author zl
 * @date 2018/10/9
 */
public class JavaDemo {

    /**
     * 课时59 ********************************
     */
    /*public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();

        for(int x = 'a'; x < 'z'; x++){
            stringBuffer.append((char)x);
        }
        //反转处理
        stringBuffer.reverse().delete(0,4);
        System.out.println(stringBuffer.toString());
    }*/

    /**
     * 课时60 ********************************
     */
    /*public static void main(String[] args) {
        int data[] = NumberFactory.create(10);
        System.out.println(Arrays.toString(data));
    }*/

    /**
     * 课时61 ********************************
     */
    public static void main(String[] args) {
        if(args.length <= 1){
            System.out.println("error");
            System.exit(1);
        }
        String email = args[0];
        if(!Validator.isEmail(email)){
            System.out.println("地址有误");
        }else {
            System.out.println("地址正确");
        }
    }


}
class NumberFactory{
    private static Random random = new Random();

    public static int [] create(int len){
        int data[] = new int[len];
        int foot = 0;
        while(foot < len){
            //开辟新的数组
            int num = random.nextInt(30);
            if(num != 0){
                //保存数组
                data[foot ++] = num;
            }
        }
        return data;
    }
}
//定义一个专门的验证程序类
class Validator{
    private Validator(){

    }
    public static boolean isEmail(String email){
        if(email == null || "".equals(email)){
            return false;
        }
        String regex = "\\w+@\\w+\\.\\w+";
        return email.matches(regex);
    }

}
