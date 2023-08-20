package x.y.z;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorWatcherTest {

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

    // 监听指定节点
    @Test
    public void testNodeCache() throws Exception {
        NodeCache nodeCache = new NodeCache(client,"/app1");

        // 注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node changed");
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println(new String(data));
            }
        });

        // 开启监听,如果设置为true,则开启监听,加载缓存数据
        nodeCache.start(true);

        while (true) {}
    }

    // 监听指定节点的所有子节点
    @Test
    public void testPathChildrenCache() throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client,"/app4",true);

        // 注册监听
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("child changed");
                System.out.println(pathChildrenCacheEvent);
                PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                if (type == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
                    System.out.println("data changed");
                    byte[] data = pathChildrenCacheEvent.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });

        // 开启监听
        pathChildrenCache.start();

        while (true) {}
    }

    // 监听指定节点的所有子节点
    @Test
    public void testTreeCache() throws Exception {
        TreeCache treeCache = new TreeCache(client,"/app4");

        // 注册监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println("child changed");
                System.out.println(treeCacheEvent);
            }
        });

        // 开启监听
        treeCache.start();

        while (true) {}
    }
}
