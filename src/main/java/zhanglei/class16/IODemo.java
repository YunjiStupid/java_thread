package zhanglei.class16;

import java.io.*;

/**
 * 课时72 流的基本概念
 *      File类是唯一一个与文件本身有关的程序处理类，但是File只能操作文件本身而不能够操作文件的内容
 * 而IO操作的核心意义在于：输入输出操作
 *      对于服务器或者是客户端而言实质上传的是一种数据流的处理形式，而所谓的数据流指的就是字节数据，
 * 而对于这种流的处理形式在java.io包里面，提供有两类支持：
 *          ·字节处理流：OutputStream(输出字符流)/InputStream(输入字节流)
 *          ·字符处理流：Writer(输出字符流)、Reader(输入字符流)
 *      所有的流操作都应该采用如下同移的步骤进行，下面以文件处理的流程为例
 *          ·如果现在要进行的是文件的读写操作，则一定要通过File类找到一个文件
 *          ·通过字节流或者字符流的子类为父类实例化
 *          ·利用字节流或字符流的方法实现数据的输入与输出操作
 *          ·流的操作属于资源操作，资源操作最后必须关闭处理
 *
 * 课时73 OutputStream字节输出流
 *      字节的数据是以byte类型为主实现的操作，在进行字节内容输出的时候可以使用OutputStream类完成
 * public abstract class OutputStream extends Object implements Closeable,Flushable
 *      OutputStream是一个公共的输出操作标准，而在这个操作标准里面定义有三个内容输出的方法
 *          ·输出单个字节数据：public abstract void write(int b) throws IOException
 *          ·输出一组字节数据(最重要)：public void write(byte[] b) throws IOException
 *          ·输出部分字节数据：public void write(byte[] b,int off,int len)throws IOException
 *      FileOutputStream子类
 *          ·【覆盖】构造函数：public FileOutputStream(File file) throws FileNotFoundException
 *          ·【追加】构造方案“public FileOutputStream(File file,boolean append) throws FileNotFoundException
 *
 * 课时74 InputStream字节输入流
 *      InputStream类主要实现的是字节输入读取，在InputSream类中定义有如下的方法
 *          ·读取单个字节数据：public abstract int read() throws IOException 如果现在已经读取到底了，返回-1,如果
 *          ·读取一组字节数据：public int read(byte [] b) throws IOException 读取一组字节数据，返回的是读取的个数，如果没有数据已经读取到底，返回-1
 *          ·读取一组字节数据的部分内容：public int read(byte [] b,int off,int len) throws IOException
 *          ·JDK1.9开始在InputStream类中新增了一个新的方法public byte[] readAllBytes() throws IOException（数据量太大时，不能使用）
 *
 * 课时75 Writer字符输出流
 *      在Writer类中提供有许多输出方法，重点来看两个：
 *          ·输出字符数组：public void write(char[] cbuf) throws IOException
 *          ·输出字符串：public void Write(String str) throws IOException
 *
 * 课时76 Reader字符输入流
 *      Reader是实现字符输入流的一种类型，其本身是抽象类，其主体定义如下
 * public abstract classReader extends,Object implements Readable,Closeable
 *      Reader类里面 并没有像Writer类一样提供有整个字符串的输出处理
 *          ·接受数据：public int read(char[] cbuf) throws IOException
 *      字符流读取的时候只能按照数组的形式来实现处理操作
 *
 * 课时77 字节流和字符流的区别
 *      重点分析一下输出的处理操作：在使用OutputStream和Writer输出的最后都使用了close()方法进行了关闭处理
 *      在使用OutputStream类输出的时候，如果没有使用close()方法关闭输出流，依然可以正常的输出，但是在使用Writer的
 * 时候没有关闭输出流，那么这个时候内容将无法进行输出。因为Writer使用到了缓冲区，使用close(0方法的时候实际上会出现有强制刷新缓冲区
 * 的情况，所以这个时候会将内容进行输出；如果没有关闭，那么无法进行输出操作，所以此时如果在不关闭的情况下要想将全部的内容输出可以使用flush()
 * 方法，强制性清空。
 *          ·字节流在进行处理的时候不会使用到缓冲区，而字符流会使用到缓冲区。另外使用缓冲区的字符流更加适合于进行中文数据操作
 *
 * 课时78 转换流
 *      转换流指的是可以实现字节流与字符流操作的功能转换。例如：进行输出的时候OutputStream需要将内容变为字节数组后才可以输出
 * 而Writer可以直接输出字符串，这一点是方便的，所以很多人就认为需要提供有一种转换机制，为此在java.io包里提供有两个类：
 * InputStreamReader
 *      ·定义：public class OutputStreamWriter extends Writer
 *      ·构造方法：public class OutputStreamWriter extends Writer
 * OutputStreamWriter
 *      ·定义：public class OutputStreamWriter extends Writer
 *      ·构造方法：public OutputStreamWriter(OutputStream out)
 *
 *      所谓的转换处理就是将收到的字节流数据对象通过向上转型变为字符流对象
 * 讲解转换流主要目的不是为了让开发者记住它，而是知道有这样一种功能，更多的是需要进行分析处理，
 * 通过之前的字节流与字符流的一系列的分析之后，会发现OutputStream类有FileOutputStream直接
 * 子类，InputStream类有FileInputStream直接子类，但是来观察一下FileWriter、FileReader的继承关系
 * 二者均是继承自转换流
 *
 *
 * 课时79 综合实战：文件拷贝
 *      在操作系统里面有一个copy命令，这个命令的主要功能是可以实现文件的拷贝处理，现在要求模拟这个命令，通过初始化参数
 * 输入拷贝的源文件路径与拷贝的目标路径实现文件的拷贝处理。
 *      需求分析：
 *          ·需要实现文件的拷贝操作：那么这种拷贝就有可能拷贝各种类型的文件，所以肯定使用字节流
 *          ·在进行拷贝的时候可能需要考虑到大文件的拷贝问题
 *      实现方案：
 *          ·方案一：使用InputStream将全部要拷贝的内容直接读取到程序里面，而后一次性的输出到目标文件；
 *              -|如果现在拷贝的文件很大，程序会崩溃
 *          ·方案二：采用部分拷贝，读取一部分，输出一部分。若要采用方案二，核心的操作方法：
 *              -|InputStream public int read(byte [] b) throws IOException
 *              -|OutputStream public void write(byte [] b,int off,int len) throws IOException
 *
 *
 *
 *
 *
 * @author zl
 * @date 2018/10/12
 */
public class IODemo {
    /*public static void main(String[] args) throws Exception {
        //1.指定要操作的文件的路径
        File file = new File("d:" + "hello" + File.separator +"dwdw.txt");
        //文件不存在
        if(!file.getParentFile().exists()){
            //创建父目录
            file.getParentFile().mkdirs();
        }
        //2.通过子类实例化
        OutputStream outputStream = new FileOutputStream(file);
        //要输出的文件内容
        String str = "张磊";
        //3.将字符串变为字节数组并输出
        outputStream.write(str.getBytes());
        //4.关闭资源
        outputStream.close();

    }*/

    /**
     * 课时74*************************************
     */
    /*public static void main(String[] args) throws Exception {
        //1.指定要操作的文件的路径
        File file = new File("d:" + "hello" + File.separator +"dwdw.txt");
        //文件不存在
        if(!file.getParentFile().exists()){
            //创建父目录
            file.getParentFile().mkdirs();
        }

        InputStream inputStream = new FileInputStream(file);

        byte data [] = new byte [1024];

        int len = inputStream.read(data);
        System.out.println("[" + new String(data,0,len) +"]");
    }*/

    /**
     * 课时75*************************************
     */
    /*public static void main(String[] args) throws Exception {
        //1.指定要操作的文件的路径
        File file = new File("d:" + "hello" + File.separator +"dwdw.txt");
        //文件不存在
        if(!file.getParentFile().exists()){
            //创建父目录
            file.getParentFile().mkdirs();
        }

        Writer out = new FileWriter(file);
        String str  = "zhangleidwdwwdwdwwddwdwdwFGRHTjyj,jmhngf";
        out.write(str);
        out.close();
    }*/

    /**
     * 课时76***************************************
     */
    /*public static void main(String[] args) throws Exception {
        //1.指定要操作的文件的路径
        File file = new File("d:" + "hello" + File.separator +"dwdw.txt");
        if(file.exists()){
            Reader in = new FileReader(file);
            char data[] = new char[1024];
            int len = in.read(data);
            System.out.println("读取内容：" + new String(data,0,len));
        }
    }*/

    /**
     * 课时78***************************************
     */
    /*public static void main(String[] args) throws Exception {
        //1.指定要操作的文件的路径
        File file = new File("d:" + "zz" + File.separator +"dwdw.txt");
        if(!file.getParentFile().exists()){
            //创建父目录
            file.getParentFile().mkdirs();
        }
        OutputStream outputStream = new FileOutputStream(file);
        //字节流变为字符流
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write("威锋网给我WWEFGYJ");
        writer.close();
        outputStream.close();
    }*/

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("命令执行错误");
            System.exit(1);
        }
        long start = System.currentTimeMillis();
        FileUtil fu = new FileUtil(args[0],args[1]);
        System.out.println(fu.copy() ? "文件拷贝成功" : "文件拷贝失败");
        long end = System.currentTimeMillis();
        System.out.println("拷贝完成时间：" + (end - start));
    }

}

/**
 * 定义一个文件操作的工具类
 */
class FileUtil{
    private File srcFile;
    private File desFile;

    public FileUtil(String src,String des){
        this(new File(src),new File(des));
    }

    public FileUtil(File srcFile,File desFile){
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    /**
     * 文件的copy
     * @author zl
     * @date 2018/10/23
     * @param
     * @return boolean
     */
    public boolean copy() throws IOException{
        if(!this.srcFile.exists()){
            System.out.println("源文件不存在");
            return false;
        }
        //判断是否有目录
        if(!this.desFile.getParentFile().exists()){
            //创建父目录
            this.desFile.getParentFile().mkdirs();
        }
        //开辟一个拷贝的缓冲区
        byte data [] = new byte[1024];
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{

            inputStream = new FileInputStream(this.srcFile);
            outputStream = new FileOutputStream(this.desFile);
            int len = 0;
            //读取数据到数组之中，随后返回读的个数
            //判断个数是否-1，如果不是则进行写入
            //开始拷贝
            while(inputStream.read(data) != -1){
                outputStream.write(data,0,len);
            }

        return true;
        }catch (IOException e){
            throw e;
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }

        }
    }
}
