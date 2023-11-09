package com.noodles.ch04.startup;


import com.noodles.ch04.core.SimpleContainer;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * @Description 启动类
 * @Author liuxian
 * @Date 2023/11/8 17:53
 **/
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        SimpleContainer container = new SimpleContainer();
        connector.setContainer(container);
        try {
            connector.initialize();
            connector.start();

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
