package com.noodles.ch09.core;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/9 15:53
 **/
public class SimpleWrapperValve implements Valve, Contained {

    protected Container container;

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        System.out.println("basic valve execute ....");
        SimpleWrapper wrapper = (SimpleWrapper)getContainer();
        ServletRequest sreq = request.getRequest();
        ServletResponse sres = response.getResponse();
        Servlet servlet = null;
        HttpServletRequest hreq = null;
        if (sreq instanceof HttpServletRequest) {
            hreq = (HttpServletRequest)sreq;
        }
        HttpServletResponse hres = null;
        if (sres instanceof HttpServletResponse) {
            hres = (HttpServletResponse) sres;
        }

        Context context = (Context)wrapper.getParent();
        request.setContext(context);

        // Allocate a servlet instance to process this request
        try {
            servlet = wrapper.allocate();
            if (hres != null && hreq != null) {
                servlet.service(hreq, hres);
            } else {
                servlet.service(sreq, sres);
            }
        } catch (ServletException e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String getInfo() {
        return null;
    }

}
