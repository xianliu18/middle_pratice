package com.noodles.ch20.standardmbeantest;

/**
 * @description: TODO
 * @author: liuxian
 * @date: 2023-11-28 22:41
 */
public class Car implements CarMBean {

    private String color = "red";

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void drive() {
        System.out.println("Baby you can drive my car.");
    }
}
