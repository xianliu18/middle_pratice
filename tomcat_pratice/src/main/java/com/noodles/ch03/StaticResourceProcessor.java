package com.noodles.ch03;


import com.noodles.ch03.connector.http.HttpRequest;
import com.noodles.ch03.connector.http.HttpResponse;

import java.io.IOException;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/6 12:10
 **/
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
