package com.noodles.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

/**
 * @Description 服务端
 * @Author liuxian
 * @Date 2023/11/3 18:07
 **/
public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("服务器启动成功，端口号为：" + ss.getLocalPort());
        // 获取客户端 socket
        Socket client = ss.accept();

        System.out.println("客户端：" + ss.getInetAddress().getHostAddress() + " connected!");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("客户端发送的数据为：" + line);
            out.println(line.toUpperCase());
        }

        client.close();
        ss.close();

    }
}
