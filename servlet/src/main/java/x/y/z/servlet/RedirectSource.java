package x.y.z.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/redirectSource")
public class RedirectSource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("redirectSource");

        // 重定向
//        resp.setStatus(302);
//        resp.setHeader("location","/servlet/redirectTarget");

        // 简化方式
        //resp.sendRedirect("/servlet/redirectTarget");

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath+"/redirectTarget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
