package com.noodles.ch15.digestertest;

import java.util.ArrayList;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/28 14:49
 **/
public class Employee {
    private String firstName;
    private String lastName;
    private ArrayList offices = new ArrayList();

    public Employee() {
        System.out.println("Creating Employee");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.println("Setting firstName: " + firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.println("Setting lastName: " + lastName);
        this.lastName = lastName;
    }

    public ArrayList getOffices() {
        return offices;
    }

    public void addOffice(Office office) {
        System.out.println("Adding office to this employee");
        offices.add(office);
    }

    public void printName() {
        System.out.println("My name is " + firstName + " " + lastName);
    }
}
