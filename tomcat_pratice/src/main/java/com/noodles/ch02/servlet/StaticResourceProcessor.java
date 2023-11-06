package com.noodles.ch02.servlet;

import java.io.IOException;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/6 12:10
 **/
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
