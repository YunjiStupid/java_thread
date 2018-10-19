package zhanglei.class9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 课时36  Date日期处理类
 *      ·将long转为Date：public Date(long date);
 *      ·将Date转为long：public long getTime();
 *    long之中可以保存毫秒的数据集，这样方便程序处理
 *
 *
 * 课时37  SimpleDateFormat日期处理类
 *      ·【DateFormat继承】将日期格式化：public final String format(Date date)
 *      ·构造方法：public SimpleDateFormat(String pattern)
 *      ·【DateFormat继承】将字符串转为日期：public Date parse(String source)
 *          -日期格式：年(yyyy)、月(MM)、日(dd)、时(HH)、分(mm)、秒(ss)、毫秒(SSS)
 *
 *
 * @author zl
 * @date 2018/9/28
 */
public class DataDemo {

    /**
     * 课时37************************************
     */
    /*public static void main(String[] args) {
        Date date = new Date();
        long current = date.getTime();
        //加上十天的秒数
        current += 864000000;
        System.out.println(new Date(current));
    }*/

    /**
     * 课时38************************************
     */
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String str = sdf.format(date);
        System.out.println(str);

        String birthday = "08:30:00.0000";
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss.SSSS");
        Date date1 = sdf2.parse(birthday);
        System.out.println(date1.getTime());

        String birthday2 = "23:00:00.0000";
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss.SSSS");
       Date date2 = sdf3.parse(birthday2);
        System.out.println(date2.getTime());
    }
}
