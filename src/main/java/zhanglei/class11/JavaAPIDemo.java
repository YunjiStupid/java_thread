package zhanglei.class11;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * 课时43  Locale类
 *    现在要自动获取当前的运行环境，可以通过Locale类本身方法
 *      ·读取本地默认环境：public static Locale getDefault()
 *    使用常量的优势在于避免一些区域编码信息的繁琐
 *
 *
 * 课时44  读取资源文件：ResourceBundle类
 *    ·获取ResourceBundle类对象：public static final ResourceBundle getBundle(String baseName);
 *      -|baseName：描述的是资源文件的名称，但是没有后缀
 *    ·根据key读取资源内容：public final String getString(String key)
 *
 *
 *
 *
 *
 *
 * @author zl
 * @date 2018/9/30
 */
public class JavaAPIDemo {
    /**
     * 课时43*******************************
     */
    /*public static void main(String[] args)throws Exception {
        Locale loc = Locale.CHINA;
        System.out.println(loc);
    }*/

    /**
     * 课时44******************************* 此代码有问题
     */
    public static void main(String[] args)throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String val = resource.getString("info");
        System.out.println(val);
    }
}
