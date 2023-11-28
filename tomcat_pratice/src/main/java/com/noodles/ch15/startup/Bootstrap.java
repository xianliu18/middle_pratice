package com.noodles.ch15.startup;

import com.noodles.ch13.core.SimpleContextConfig;
import org.apache.catalina.Connector;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;

import java.io.File;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/28 14:49
 **/
public class Bootstrap {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "tomcat-webroot";

    public static void main(String[] args) {
        System.setProperty("catalina.base", WEB_ROOT);
        Connector connector = new HttpConnector();

        Context context = new StandardContext();
        context.setPath("/app1");
        context.setDocBase("app1");

        LifecycleListener listener = new ContextConfig();
        ((Lifecycle)context).addLifecycleListener(listener);

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        connector.setContainer(host);

        try {
            connector.initialize();
            ((Lifecycle)connector).start();
            ((Lifecycle)host).start();
            Container[] c = context.findChildren();
            for (int i = 0; i < c.length; i++) {
                Container child = c[i];
                System.out.println(child.getName());
            }

            System.in.read();
            ((Lifecycle)host).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
