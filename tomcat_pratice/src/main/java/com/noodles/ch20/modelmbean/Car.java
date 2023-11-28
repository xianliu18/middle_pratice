package com.noodles.ch20.modelmbean;

/**
 * @description: TODO
 * @author: liuxian
 * @date: 2023-11-28 23:09
 */
public class Car {

    private String color = "red";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void drive() {
        System.out.println("Baby you can drive my car.");
    }

}
