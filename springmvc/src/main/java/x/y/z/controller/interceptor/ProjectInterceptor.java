package x.y.z.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Filter属于Servlet技术，Interceptor属于SpringMVC的技术(仅对Controller层有效)
//Filter对所有访问进行增强，Interceptor仅针对SpringMVC的访问进行增强
@Component
public class ProjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        String contentType = request.getHeader("Content-Type");
        System.out.println(contentType);
        System.out.println(handler.getClass());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        System.out.println(handlerMethod.getMethod());
        return true; // true:继续执行 false:中止执行
    }

    // ModelAndView:封装了SpringMVC页面调整的相关数据
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    // Exception:如果执行处理器执行过程中出现异常对象,可以针对异常对象进行单独处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
