package com.noodles.ch09.startup;

import com.noodles.ch09.core.SimpleContextConfig;
import com.noodles.ch09.core.SimpleWrapper;
import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Manager;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.session.StandardManager;

import java.io.File;

/**
 * @Description 启动类
 * @Author liuxian
 * @Date 2023/11/17 11:13
 **/
public class Bootstrap {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "tomcat-webroot";

    public static void main(String[] args) {
        System.setProperty("catalina.base", WEB_ROOT);
        Connector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Session");
        wrapper1.setServletClass("SessionServlet");

        Context context = new StandardContext();
        context.setPath("/myApp");
        context.setDocBase("myApp");

        context.addChild(wrapper1);

        context.addServletMapping("/myApp/Session", "Session");

        LifecycleListener listener = new SimpleContextConfig();
        ((Lifecycle)context).addLifecycleListener(listener);

        Loader loader = new WebappLoader();
        context.setLoader(loader);

        connector.setContainer(context);

        Manager manager = new StandardManager();
        context.setManager(manager);

        try {
            connector.initialize();
            ((Lifecycle)connector).start();
            ((Lifecycle)context).start();

            // make the application wait until we press a key.
            System.in.read();
            ((Lifecycle)context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
