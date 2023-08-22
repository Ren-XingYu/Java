package x.y.z.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // 配置类
@ComponentScan({"x.y.z"})
@PropertySource({"classpath:jdbc.properties"}) // 多文件使用数组的格式,不支持使用通配符
// @ComponentScan("x.y.z.config") // 需要配合@Configuration注解使用
@Import({JdbcConfig.class})
@EnableAspectJAutoProxy // 告诉Spring,启用AspectJ
@EnableTransactionManagement // 告诉Spring,启用事务管理
public class SpringConfig {
}
