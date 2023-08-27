package x.y.z.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
// Spring相关bean加载控制
@ComponentScan({"x.y.z.dao","x.y.z.service"})
//@ComponentScan(value = "x.y.z",excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class))
public class SpringConfig {
}
