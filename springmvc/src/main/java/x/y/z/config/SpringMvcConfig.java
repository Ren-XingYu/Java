package x.y.z.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
// SpringMVC相关bean加载控制
@ComponentScan({"x.y.z.controller","x.y.z.config"})
@EnableWebMvc // 开启springmvc辅助功能
public class SpringMvcConfig {
}
