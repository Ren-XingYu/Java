package x.y.z.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class FilterLifeCycle implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 放行前逻辑
        System.out.println("doFilter begin");

        filterChain.doFilter(servletRequest,servletResponse); // 放行

        // 放行后逻辑
        System.out.println("doFilter end");
    }
}
