package com.noodles.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Description socket 使用
 *          需求：客户端输入字母发送给服务端，服务端将字母转换为大写后返回给客户端，指导客户端输入 over，结束通信
 *          参考链接：https://blog.csdn.net/gwx324657/article/details/81096652
 * @Author liuxian
 * @Date 2023/11/3 18:00
 **/
public class SocketClientDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream output = socket.getOutputStream();
        boolean autoFlush = true;
        PrintWriter out = new PrintWriter(output, autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("客户端启动成功，请输入数据：");
        // send an HTTP request to the web server
        String line;
        // 获取键盘录入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = bufferedReader.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            out.println(line);
            // 读取服务端返回的一行大写数据
            String str = in.readLine();
            System.out.println("服务端返回的数据为：" + str);
        }


        socket.close();
    }
}
