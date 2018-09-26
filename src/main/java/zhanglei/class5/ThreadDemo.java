package zhanglei.class5;

/**
 * @author zl
 * @date 2018/9/25
 */
/**
 * 课时21
 * 守护线程，如果现在主线程还在运行，那么守护线程将一直存在，并且运行在后台状态
 *    在Thread类里面提供有如下的守护线程的操作方法
 *    ·设置为守护线程： public final void setDaemon(boolean on);
 *    `判断是否为守护线程：public final boolean is Daemon();
 *    最大的守护线程则为GC线程，如果程序执行完毕，GC线程也将消失
 *
 *
 *
 * 课时22  volatile关键字
 *     volatile关键字主要是在属性定义上进行操作的，表示此属性为直接数据操作，而不进行副本的拷贝处理（不是同步属性）
 *     在正常进行变量处理的时候，旺旺会有如下操作
 *          ·获取标量原有的数据内容副本
 *          ·利用副本为变量进行数据计算
 *          ·将计算后的变量，保存到原始空间之中
 *     如果一个属性上追加volatile关键字，表示不使用副本，直接操作原始变量
 *     面试题：请解释 volatile 与 synchronized 的区别
 *          ·volatile主要用在属性上，synchronized用在代码块和方法上
 *          ·volatile无法描述同步处理，它只是一种直接内存的处理，避免了副本操作
 */
public class ThreadDemo {
    public static boolean flag = true;
    public static void main(String[] args) throws Exception {
        /*
         * 停止线程
        new Thread(()->{
            long num = 0;
            while(flag){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                + "正在运行,num" + num++);
            }
        }).start();
        Thread.sleep(200);
        flag = false;*/

        /**
         * 守护线程**************************************
         * @author zl
         * @date 2018/9/25
         * @param args
         * @return void
         */
        /*Thread userThread = new Thread(()->{
                for(int x = 0; x < 1; x ++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "正在运行,x = " + x);
                }
        },"用户线程");

        Thread daemonThread = new Thread(()->{
            for(int x = 0; x < Integer.MAX_VALUE; x ++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + "正在运行,x = " + x);
            }
        },"守护线程");
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();
    }*/
        MyThread mt = new MyThread();
        new Thread(mt,"票贩子A").start();
        new Thread(mt,"票贩子B").start();
        new Thread(mt,"票贩子C").start();
    }

}
class MyThread implements Runnable {
    private volatile int ticket = 5;

    @Override
    public void run() {
        while (this.ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "卖票处理,ticket = " + this.ticket--);
        }
    }
}
