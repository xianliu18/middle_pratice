package com.noodles.ch08.startup;

import com.noodles.ch08.core.SimpleContextConfig;
import com.noodles.ch08.core.SimpleWrapper;
import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.naming.resources.ProxyDirContext;

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
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new StandardContext();
        context.setPath("/myApp");
        context.setDocBase("myApp");

        context.addChild(wrapper1);
        context.addChild(wrapper2);

        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");

        LifecycleListener listener = new SimpleContextConfig();
        ((Lifecycle)context).addLifecycleListener(listener);

        Loader loader = new WebappLoader();
        context.setLoader(loader);

        connector.setContainer(context);

        try {
            connector.initialize();
            ((Lifecycle)connector).start();
            ((Lifecycle)context).start();
            WebappClassLoader classLoader = (WebappClassLoader) loader.getClassLoader();
            System.out.println("Resources' docBase: " + ((ProxyDirContext)classLoader.getResources()).getDocBase());
            String[] repositories = classLoader.findRepositories();
            for (int i = 0; i < repositories.length; i++) {
                System.out.println("\t repository: " + repositories[i]);
            }

            // make the application wait until we press a key.
            System.in.read();
            ((Lifecycle)context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
