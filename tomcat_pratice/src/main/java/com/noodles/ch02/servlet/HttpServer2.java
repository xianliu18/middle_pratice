package com.noodles.ch02.servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 测试自定义的 servlet
 *          运行之前，先编译 tomcat-webroot 目录下的 PrimitiveServlet.java
 *          命令：javac -encoding UTF-8 -classpath ./servlet-api-2.4.jar .\PrimitiveServlet.java
 * @Author liuxian
 * @Date 2023/11/6 12:10
 **/
public class HttpServer2 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("httpServer1 正常启动，端口号为：" + port + "...");

        // Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request object and parse
                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                System.out.println("httpServer1 出现异常...");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
