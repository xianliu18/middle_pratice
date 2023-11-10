package com.noodles.ch05.startup;

import com.noodles.ch05.core.SimpleContext;
import com.noodles.ch05.core.SimpleContextLifecycleListener;
import com.noodles.ch05.core.SimpleContextMapper;
import com.noodles.ch05.core.SimpleLoader;
import com.noodles.ch05.core.SimpleWrapper;
import com.noodles.ch05.values.ClientIPLoggerValue;
import com.noodles.ch05.values.HeaderLoggerValue;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/9 18:51
 **/
public class Bootstrap2 {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);

//        Valve valve1 = new HeaderLoggerValue();
//        Valve valve2 = new ClientIPLoggerValue();
//
//        ((Pipeline)context).addValve(valve1);
//        ((Pipeline)context).addValve(valve2);

        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        context.addMapper(mapper);
//        Mapper mapper2 = new SimpleContextMapper();
//        mapper2.setProtocol("https");
//        context.addMapper(mapper2);
//        System.out.println("mapper: " + ((SimpleContext)context).mapper);
        LifecycleListener listener = new SimpleContextLifecycleListener();
        ((Lifecycle)context).addLifecycleListener(listener);
        Loader loader = new SimpleLoader();
        context.setLoader(loader);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");
        connector.setContainer(context);
        try {
            connector.initialize();
            ((Lifecycle)connector).start();
            ((Lifecycle)context).start();

            System.in.read();

            ((Lifecycle)context).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
