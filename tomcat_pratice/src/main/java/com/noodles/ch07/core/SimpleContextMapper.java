package com.noodles.ch07.core;

import org.apache.catalina.Container;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.Mapper;
import org.apache.catalina.Request;
import org.apache.catalina.Wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/9 17:55
 **/
public class SimpleContextMapper implements Mapper {

    private SimpleContext context = null;

    /**
     * The protocol with which this Mapper is associated.
     */
    private String protocol = null;

    @Override
    public Container getContainer() {
        return context;
    }

    @Override
    public void setContainer(Container container) {
        if (!(container instanceof SimpleContext)) {
            throw new IllegalArgumentException("Illegal type of container");
        }
        context = (SimpleContext) container;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public Container map(Request request, boolean update) {
        // Identify the context-relative URI to be mapped
        String contextPath = ((HttpServletRequest)request.getRequest()).getContextPath();
        String requestURI = ((HttpRequest)request).getDecodedRequestURI();
        String relativeURI = requestURI.substring(contextPath.length());
        Wrapper wrapper = null;
        String servletPath = relativeURI;
        String pathInfo = null;
        String name = context.findServletMapping(relativeURI);
        if (name != null) {
            wrapper = (Wrapper)context.findChild(name);
        }
        return wrapper;
    }
}
