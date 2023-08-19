package x.y.z.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * loadOnStartup: 负整数:第一次被访问的时候创建  0和正整数:服务器启动的时候创建,数值越小优先级越高
 */
@WebServlet(urlPatterns = "/demo1",loadOnStartup = 0)
public class LifeCycleDemo implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        System.out.println("servlet init.");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("servlet ServletConfig.");
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet hello world.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        if ("GET".equals(method)) {
            System.out.println("request by GET.");
        } else if ("POST".equals(method)) {
            System.out.println("request by POST.");
        } else {
            System.out.println("method not allowed.");
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet destroy.");
    }
}
