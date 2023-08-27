package x.y.z.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"x.y.z.service","x.y.z.dao"})
@PropertySource({"classpath:jdbc.properties"})
@Import({JdbcConfig.class,MybatisConfig.class})
@EnableTransactionManagement // 开启事务
public class SpringConfig {
}
