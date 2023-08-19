package x.y.z.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet(urlPatterns = "/req")
public class EncodingDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Tomcat8.0之后,已经将默认的编码设置为 UTF-8
        // POST 方式解决中文乱码
        // req.setCharacterEncoding("UTF-8");

        // http://localhost:8080/servlet/req?name=zhangsan&password=123

        // 获取请求行中的数据
        String method = req.getMethod();
        System.out.println(method); // GET

        String contextPath = req.getContextPath();
        System.out.println(contextPath); // /servlet

        StringBuffer reqUrl = req.getRequestURL();
        System.out.println(reqUrl); // http://localhost:8080/servlet/req

        String reqUri = req.getRequestURI();
        System.out.println(reqUri); // /servlet/req

        // GET方式获取请求参数
        String queryString = req.getQueryString();
        System.out.println(queryString); // name=zhangsan&password=123

        // 获取请求头中的数据
        String userAgent = req.getHeader("User-Agent");
        System.out.println(userAgent); // Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36


        // 通用获取请求参数的方式
        Map<String,String[]> parameterMap = req.getParameterMap();
        System.out.println(parameterMap.get("username")[0]);

        String[] hobby = req.getParameterValues("hobby");
        System.out.println(hobby[0]);

        String username = req.getParameter("username");
        System.out.println(username);

        // GET/POST 方式解决中文乱码
        System.out.println(new String(username.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8080/servlet/req.html

        // POST方式获取请求参数(通过流获取)

        // 获取请求体中的数据
        //InputStream is = req.getInputStream(); // 字节流

//        BufferedReader br = req.getReader(); // 字符流
//        System.out.println(br.readLine());
        this.doGet(req, resp);
    }
}
