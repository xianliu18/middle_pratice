package com.noodles.ch05.startup;

import com.noodles.ch05.core.SimpleLoader;
import com.noodles.ch05.core.SimpleWrapper;
import com.noodles.ch05.values.ClientIPLoggerValue;
import com.noodles.ch05.values.HeaderLoggerValue;
import org.apache.catalina.Loader;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/9 17:39
 **/
public class Bootstrap1 {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper = new SimpleWrapper();
        wrapper.setServletClass("ModernServlet");
        Loader loader = new SimpleLoader();
        Valve valve1 = new HeaderLoggerValue();
        Valve valve2 = new ClientIPLoggerValue();

        wrapper.setLoader(loader);
        ((SimpleWrapper)wrapper).addValve(valve1);
        ((SimpleWrapper)wrapper).addValve(valve2);

        connector.setContainer(wrapper);

        try {
            connector.initialize();
            connector.start();

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
