package com.noodles.ch20.standardmbeantest;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @description: TODO
 * @author: liuxian
 * @date: 2023-11-28 22:47
 */
public class StandardAgent {

    private MBeanServer mBeanServer = null;
    public StandardAgent() {
        mBeanServer = MBeanServerFactory.createMBeanServer();
    }

    public MBeanServer getMBeanServer() {
        return mBeanServer;
    }

    public ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return objectName;
    }

    private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
        try {
            mBeanServer.createMBean(managedResourceClassName, objectName);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        StandardAgent agent = new StandardAgent();
        MBeanServer mBeanServer = agent.getMBeanServer();
        String domain = mBeanServer.getDefaultDomain();
        String managedResourceClassName = "com.noodles.ch20.standardmbeantest.Car";
        ObjectName objectName = agent.createObjectName(domain + ":type=" + managedResourceClassName);
        agent.createStandardBean(objectName, managedResourceClassName);

        try {
            System.out.println("domain: " + domain);
            Attribute colorAttribute = new Attribute("Color", "blue");
            mBeanServer.setAttribute(objectName, colorAttribute);
            System.out.println(mBeanServer.getAttribute(objectName, "Color"));
            mBeanServer.invoke(objectName, "drive", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
