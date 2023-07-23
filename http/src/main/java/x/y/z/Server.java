package x.y.z;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server is running......");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("connection from " + socket.getRemoteSocketAddress());
            Thread thread = new Handler(socket);
            thread.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            InputStream is = this.socket.getInputStream();
            OutputStream os = this.socket.getOutputStream();
            handle(is,os);
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (Exception se) {

            }
            System.out.println("client "+socket.getRemoteSocketAddress()+" disconnected");
        }
    }

    /**
     * http报文格式
     *
     * 请求报文:
     *  请求行
     *  请求头
     *  请求体
     * 响应报文:
     *  响应行
     *  响应头
     *  响应体
     *
     *  Web服务器是一个应用程序(软件),对http协议的操作进行封装(解析请求,发送响应),使得程序员不必直接对协议进行操作(socket编程),让web开发更加便捷
     *
     *  Tomcat支持Servlet/JSP少量JavaEE规范, Servlet需要依赖于Tomcat才能运行
     *  Servlet是Java提供的一门动态web资源开发技术
     */
    private void handle(InputStream is, OutputStream os) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,StandardCharsets.UTF_8));
        boolean requestOk = false;

        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.1")) {
            requestOk = true;
        }
        for (;;) {
            String header = reader.readLine();
            if (header.isEmpty()) { // 读取到空行时, HTTP Header读取完毕
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // 发送成功响应:

            //读取html文件，转换为字符串
            String file = System.getProperty("user.dir") +"\\http\\src\\main\\resources\\index.html";
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder data = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null){
                data.append(line);
            }
            br.close();
            int length = data.toString().getBytes(StandardCharsets.UTF_8).length;

            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Connection: keep-alive\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data.toString());
            writer.flush();
        }

    }
}
