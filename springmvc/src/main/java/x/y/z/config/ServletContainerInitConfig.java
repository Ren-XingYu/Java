package x.y.z.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;
import java.io.File;

// 定义servlet容器启动的配置类,在里面加载spring的配置
// AbstractDispatcherServletInitializer是springmvc提供的快速初始化web3.0容器的抽象类
// Web容器
//  - ServletContext
//      - WebApplicationContext
//          - UserController
//public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
//    // 加载SpringMVC容器配置
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringMvcConfig.class);
//        return ctx;
//    }
//
//    // 设置哪些请求归属SpringMVC处理
//    @Overrides
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    // 加载Spring容器配置
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringConfig.class);
//        return ctx;
//    }
//}

// 上面方式的简化
public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("utf-8");
        return new Filter[]{filter};
    }
}
