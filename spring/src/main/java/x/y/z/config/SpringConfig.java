package x.y.z.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration // 配置类
@ComponentScan({"x.y.z"})
@PropertySource({"classpath:jdbc.properties"}) // 多文件使用数组的格式,不支持使用通配符
// @ComponentScan("x.y.z.config") // 需要配合@Configuration注解使用
@Import({JdbcConfig.class})
public class SpringConfig {
}
