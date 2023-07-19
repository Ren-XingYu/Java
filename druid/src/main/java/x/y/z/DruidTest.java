package x.y.z;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void testDruid() throws Exception{
        System.out.println(System.getProperty("user.dir"));
        Properties prop = new Properties();
        prop.load(DruidTest.class.getResourceAsStream("/druid.properties"));
        // 数据库连接池都要实现DataSource接口
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
