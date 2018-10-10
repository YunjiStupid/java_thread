package zhanglei.class15;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 课时67 File类基本操作
 *      File类是Comparable类的子类，所以File类的对象是可以进行排序处理的。而在进行File类处理的时候需要为其设置访问路径，
 * 那么对于路径的配置主要通过File类的构造方法处理
 *          ·构造方法：public File(String pathname)，设置要操作完整路径
 *          ·构造方法：public File(File parent,String chile)，设置父路径和子目录
 *      如果相对文件进行操作，可使用如下方法
 *          ·创建新文件：public boolean createNewFile() throws IOException
 *          ·判断文件是否存在：public boolean exists()
 *          ·删除文件：pubic boolean delete()
 *
 * 课时68 File类操作深入
 *      1.在不同的系统之中会存在有不同的路径分隔符：
 * Windows分隔符“\”，linux分隔符“/”为了统一此问题，File类提供有一个常量：public static final String separator
 *      2.在使用File类处理的时候需要注意的是：程序-》JVM-》操作系统函数-》文件处理，所以在进行同一文件的反复删除和创建的时候，可能会出现有延迟问题，
 * 所以这个时候最好的方案是别重名
 *      3.在进行文件创建的时候，文件的父路径必须首先存在
 *          ·如何获取父路径：public File getParentFile()
 *          ·创建目录：public boolean mkdirs()，(有s为创建多级目录，没有为创建单级目录)
 *
 * 课时69 获取文件信息
 *      通过File类获取一些文件本身提供的信息，可以获取如下内容：
 *          ·可判断文件是否可读：public boolean canRead()
 *          ·文件是否可写：public boolean canWrite()
 *          ·获取文件大小：public long length(),该方法主要返回的是long数据类型
 *          ·判断是否是目录：public boolean isDirectory()
 *          ·判断是否是文件：public boolean isFile()
 *          ·列出目录内容：public File[] listFiles()
 *
 * 课时70 综合案例：列出目录结构
 *      File操作案例：列出指定目录的全部文件（包括子目录）
 *
 * 课时71 File操作案例：皮脸修改文件名称
 *      编写程序，程序运行时输入目录名称，并把该目录下的所有文件名后缀修改为.txt
 *
 *
 *
 *
 *
 *
 * @author zl
 * @date 2018/10/10
 */
public class FileDemo {
    /**
     * 课时67 File类基本操作******************************
     */
    /*public static void main(String[] args) throws IOException {
        File file = new File("d:\\dwdw.txt");
        if(file.exists()){
            System.out.println(file.delete());
        }else{
            System.out.println(file.createNewFile());
        }
    }*/

    /**
     * 课时68 File类操作深入******************************
     */
    /*public static void main(String[] args) throws IOException {
        File file = new File("d:" + File.separator +"zhanglei222" + File.separator + "dwdw.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();//创建父路径
        }
        if(file.exists()){
            System.out.println(file.delete());
        }else{
            System.out.println(file.createNewFile());
        }
    }*/

    /**
     * 课时69 获取文件信息******************************
     */
    /*public static void main(String[] args) throws IOException {
        File file = new File("d:" + File.separator);
        System.out.println("文件是否可读：" + file.canRead());
        System.out.println("文件是否可写：" + file.canWrite());
        System.out.println("文件大小：" + file.length());
        System.out.println("最后的修改时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
        if(file.isDirectory()){
            File filelist[] = file.listFiles();
            for(int x = 0; x < filelist.length; x ++ ){
                System.out.println(filelist[x]);
            }
        }
        System.out.println("是否是文件：" + file.isFile());
    }*/

    /**
     * 课时70 综合案例******************************
     */
    /*public static void main(String[] args) throws IOException {
        File file = new File("d:" + File.separator);
        listFile(file);
    }
    public static void listFile(File file){
        if(file.isDirectory()){
            File filelist [] = file.listFiles();
            if(filelist != null){
                for(int x = 0; x < filelist.length; x++){
                    listFile(filelist[x]);
                }
            }
        }
        System.out.println(file);
    }*/

    /**
     * 课时71 综合案例2******************************没写完，不想写了
     */
    public static void main(String[] args) throws IOException {
        File file = new File("d:" + File.separator + "zhanglei222");
        renameDir(file);
    }
    public static void renameDir(File file){
        if(file.isDirectory()){
            File filelist [] = file.listFiles();
            if(filelist != null){
                for(int x = 0; x < filelist.length; x++){
                    renameDir(filelist[x]);
                }
            }
        }else {
            if(file.isFile()){
                //File newFile = new File(file.getParentFile());
                String filename = file.getName().substring(file.getName().lastIndexOf("."));
                System.out.println(filename);
            }
        }
    }
}
