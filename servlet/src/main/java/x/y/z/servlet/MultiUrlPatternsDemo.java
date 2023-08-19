package x.y.z.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 一个servlet可以配置多个访问路径
@WebServlet(urlPatterns = {"/demo3","/demo4"})
public class MultiUrlPatternsDemo extends CustomServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("servlet do get.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("servlet do post.");
    }
}
