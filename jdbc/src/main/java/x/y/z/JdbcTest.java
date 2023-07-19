package x.y.z;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class JdbcTest {

    @Test
    public void testStatement() throws Exception{
        // 1、注册驱动
        // mysql5之后不用注册驱动,因为在驱动jar包中META-INF/services/java.sql.Driver中已经定义了驱动类,通过SPI自动加载
        // Class.forName("com.mysql.jdbc.Driver");
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // DriverManager.registerDriver(new Driver());

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {

            // 2、获取连接
            Properties prop = new Properties();
            prop.load(JdbcTest.class.getResourceAsStream("/jdbc.properties"));

            String url = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            conn = DriverManager.getConnection(url, username, password);

            // 3、定义SQL
            String sql = "select * from tb_user";

            // 4、获取执行SQL的对象Statement
            stat = conn.createStatement();

            // 5、执行SQL
            rs = stat.executeQuery(sql);
            // stat.executeUpdate() // 执行dml、ddl
            // stat.executeQuery() // 执行dql

            conn.setAutoCommit(false); // 设置手动事务
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String pwd = rs.getString("password");
                System.out.println("id=" + id + " username=" + name + " password=" + pwd);
            }
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
        } finally {
            // 6、释放资源
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
    }

    @Test
    public void testPrepareStatement() throws Exception{
        // 默认PreparedStatement的预编译功能没有开启,需要添加参数useServerPrepStmts=true
        Properties prop = new Properties();
        prop.load(JdbcTest.class.getResourceAsStream("/jdbc.properties"));

        String url = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");
        Connection conn = DriverManager.getConnection(url, username, password);

        String sql = "insert into tb_user(id,username,password,gender,address) values(?,?,?,?,?)";

        // 执行sql
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1,4);
        stat.setString(2,"赵六");
        stat.setString(3,"012");
        stat.setString(4,"男");
        stat.setString(5,"南京");

        stat.executeUpdate();
        stat.close();
        conn.close();
    }
}
