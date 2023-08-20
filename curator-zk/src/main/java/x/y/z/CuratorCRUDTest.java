package x.y.z;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CuratorCRUDTest {

    private CuratorFramework client;

    @Before
    public void testConnection(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,10);
        client = CuratorFrameworkFactory.builder().connectString("192.168.10.10:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy)
                .namespace("xyz")
                .build();
        client.start();
    }

    @After
    public void close(){
        if (client!=null) {
            client.close();
        }
    }

    // 基本创建
    @Test
    public void testCreate1() throws Exception {
        // 如果没有指定数据,则默认将当前客户端的ip作为数据存储
        String path = client.create().forPath("/app1");
        System.out.println(path);
    }

    // 创建带有数据的节点
    @Test
    public void testCreate2() throws Exception {
        String path = client.create().forPath("/app2","abc".getBytes());
        System.out.println(path);
    }

    // 设置节点类型(默认持久的)
    @Test
    public void testCreate3() throws Exception {
        String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3");
        System.out.println(path);
    }

    // 创建多级节点
    @Test
    public void testCreate4() throws Exception {
        String path = client.create().creatingParentsIfNeeded().forPath("/app4/p1");
        System.out.println(path);
    }

    // 查询数据
    @Test
    public void testGet1() throws Exception {
        byte[] data = client.getData().forPath("/app1");
        System.out.println(new String(data));
    }

    // 查询子节点
    @Test
    public void testGet2() throws Exception {
        List<String> children = client.getChildren().forPath("/app4");
        System.out.println(children);
    }

    // 查询节点状态
    @Test
    public void testGet3() throws Exception {
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/app1");
        System.out.println(stat);
    }

    // 修改数据
    @Test
    public void testSet() throws Exception {
        client.setData().forPath("/app1","data1".getBytes());
    }

    // 根据版本号修改
    @Test
    public void testSetWithVersion() throws Exception {
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/app1");

        int version = stat.getVersion();
        System.out.println(version);

        client.setData().withVersion(version).forPath("/app1","data1".getBytes());
    }

    // 删除单个节点
    @Test
    public void testDelete1() throws Exception {
        client.delete().forPath("/app1");
    }

    // 删除带有子节点的节点
    @Test
    public void testDelete2() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath("/app4");
    }

    // 确保删除成功(本质就是重试)
    @Test
    public void testDelete3() throws Exception {
        client.delete().guaranteed().forPath("/app2");
    }

    // 删除回调
    @Test
    public void testDelete4() throws Exception {
        client.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("delete success");
                System.out.println(curatorEvent);
            }
        }).forPath("/app1");
    }
}
