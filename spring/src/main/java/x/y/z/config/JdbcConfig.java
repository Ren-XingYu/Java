package x.y.z.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import x.y.z.dao.BookDao;

import javax.sql.DataSource;

// @Configuration
public class JdbcConfig {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("{jdbc.driverClassName}")
    private String driverClass;

    // 管理第三方Bean
    // 注入引用类型直接传递形参
    @Bean  // 表示当前方法的返回值是一个Bean
    public DataSource dataSource(BookDao bookDao){
        System.out.println(bookDao);
        System.out.println(username);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }
}
