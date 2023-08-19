package x.y.z.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/redirectTarget")
public class RedirectTarget extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("redirectTarget");

        //resp.setHeader("content-type","text/html;charset=utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 字符流
        // writer不需要关闭,resp关闭后会自动关闭
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hello</html>");


        // 字节流
        //ServletOutputStream sos = resp.getOutputStream();
        //byte[] bytes = new byte[1024];
        //sos.write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
