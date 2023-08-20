package x.y.z;

public class CuratorLockTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 创建客户端
        Thread t1 = new Thread(ticket,"t1");
        Thread t2 = new Thread(ticket,"t2");

        t1.start();
        t2.start();
    }
}
