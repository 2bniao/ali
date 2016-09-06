package com.mt.test;

import java.math.BigDecimal;

public class Parent {
    private int i = 10;

    public Parent() {
        i = 20;
        print();
    }

    public void print() {
        System.out.println(i + "===");
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("2.3");
        BigDecimal aa = new BigDecimal("2.29");
        System.out.println(a.subtract(aa));
    }
}
