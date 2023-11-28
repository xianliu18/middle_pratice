package com.noodles.ch15.digestertest;

import org.apache.commons.digester.Digester;

import java.io.File;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/28 14:55
 **/
public class Test01 {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "tomcat-webroot/etc";
        File file = new File(path, "employee1.xml");
        Digester digester = new Digester();
        // add rules
        digester.addObjectCreate("employee", "com.noodles.ch15.digestertest.Employee");
        digester.addSetProperties("employee");
        digester.addCallMethod("employee", "printName");

        try {
            Employee employee = (Employee)digester.parse(file);
            System.out.println("First name: " + employee.getFirstName());
            System.out.println("Last name: " + employee.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
