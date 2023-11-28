package com.noodles.ch15.digestertest;

/**
 * @Description
 * @Author liuxian
 * @Date 2023/11/28 14:52
 **/
public class Office {
    private Address address;
    private String description;

    public Office() {
        System.out.println("..Creating Office");
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        System.out.println("..Setting address: " + address);
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        System.out.println("..Setting description: " + description);
        this.description = description;
    }
}
