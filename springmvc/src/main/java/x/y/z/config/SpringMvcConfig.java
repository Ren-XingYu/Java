package x.y.z.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import x.y.z.controller.interceptor.ProjectInterceptor;
import x.y.z.controller.interceptor.ProjectInterceptor2;

@Configuration
// SpringMVC相关bean加载控制
// 实现WebMvcConfigurer接口后不用扫描x.y.z.config类(扫SpringMvcSupport类)
@ComponentScan({"x.y.z.controller"})
@EnableWebMvc // 开启springmvc辅助功能
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Autowired
    private ProjectInterceptor2 projectInterceptor2;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 当访问到/pages/???的时候,走/pages目录下的内容
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    // 注册拦截器:如果是多个拦截器,执行顺序以添加顺序为准
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/users/*");
        registry.addInterceptor(projectInterceptor2).addPathPatterns("/users/*");
    }
}
