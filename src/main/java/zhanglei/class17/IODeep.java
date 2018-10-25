package zhanglei.class17;

import java.io.*;

/**
 * 课时80 字符编码
 *
 * 课时81 内存操作流
 *      假设现在需要实现IO操作，可是又不希望产生文件（临时文件）
 * 则就可以以内存为终端进行处理。
 *      在JAVA中提供有两类内存操作流：
 *          ·字节内存操作流：ByteArrayOutputStream、ByteArrayInputStream
 *          ·字符内存操作流：CharArrayWriter、CharArrayReader
 *  下面以ByteArrayOutputStream和ByteArrayInputStream类为主，进行内存分析
 *      把数据先放到内存里面，然后抓出来
 *      ·ByteArrayInputStream： public ByteArrayInputStream(byte [] buf)
 *      ·ByteArrayOutputStream： public ByteArrayOutputStream()
 *  在ByteArrayOutputStream类中有一个重要方法，该方法可以获取全部保存在内存流中的数据信息：
 *      ·获取数据： public byte[] toByteArray()
 *      ·使用字符串的形式来获取：public String toString()
 *  如果现在不希望只是以字符串的形式返回，因为可能是存放的其他二进制数据，可通过其子类的扩展功能
 *  获取全部字节数据
 *
 *
 *  课时82 管道流
 *      管道流主要的功能是实现两个线程之间的IO处理操作
 *      对于管道流也分为两种：
 *          ·字节管道流：PipedOutputStream、PipedInputStream
 *              |-连接处理：public void connect(PipedInputStream snk) throws IOException
 *          ·字符管道流：PipedWriter、PipedReader
 *              |-连接处理：public void connect(PipedReader snk) throws IOException
 *
 *
 */
public class IODeep {

    /**
     * 课时81 内存操作流****************************************
     */
    /*public static void main(String[] args) throws IOException {
        String str = "zhanglei";
        //将数据保存在内存流
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        //读取内存中的数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int data = 0;
        while((data = inputStream.read()) != -1){
            //保存数据
            outputStream.write(data);
        }
        byte result[] = outputStream.toByteArray();
        System.out.println(new String(result));
        inputStream.close();
        outputStream.close();
    }*/

    /**
     * 课时82 管道流****************************
     */
   /* public static void main(String[] args) throws IOException {
        SendThread sendThread = new SendThread();
        ReceiveThread receiveThread = new ReceiveThread();
        sendThread.getOutputStream().connect(receiveThread.getInputStream());
        new Thread(sendThread,"消息发送线程").start();
        new Thread(receiveThread,"消息接收线程").start();
    }*/

    public static void main(String[] args) throws IOException {
        SendThread sendThread = new SendThread();
        ReceiveThread receiveThread = new ReceiveThread();
        sendThread.getOutputStream().connect(receiveThread.getInputStream());
        new Thread(sendThread,"消息发送线程").start();
        new Thread(receiveThread,"消息接收线程").start();
    }
}

class SendThread implements Runnable{
    private PipedOutputStream outputStream;
    public SendThread(){
        //实例化管道输出流
        this.outputStream = new PipedOutputStream();
    }
    @Override
    public void run() {
        for(int x = 0; x < 10; x++){
            try {
                this.outputStream.write(("[第" + (x + 1) + "次信息发送" + Thread.currentThread().getName()).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PipedOutputStream getOutputStream(){
        return outputStream;
    }
}

class ReceiveThread implements Runnable {
    private PipedInputStream inputStream;
    public ReceiveThread(){
        //实例化管道输入流
        this.inputStream = new PipedInputStream();
    }
    @Override
    public void run() {
        byte data [] = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = this.inputStream.read(data)) != -1){
                System.out.println("{" + Thread.currentThread().getName() + "接受信息" + new String(bos.toByteArray()));
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PipedInputStream getInputStream(){
        return inputStream;
    }
}
