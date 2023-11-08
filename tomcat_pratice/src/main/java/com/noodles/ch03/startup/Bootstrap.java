package com.noodles.ch03.startup;

import com.noodles.ch03.connector.http.HttpConnector;

/**
 * @Description 启动类
 * @Author liuxian
 * @Date 2023/11/6 16:03
 **/
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
