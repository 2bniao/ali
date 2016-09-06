package com.mt.test.t1;

public class T {
    String name;

    public T(String s) {
        name = s;
    }

    public String greet(String yourName) {
        return String.format("Hi %s, my name is %s", name, yourName);
    }

    public static void main(String[] args) {
        String p = new T("s").greet("a");
        System.out.println(p);
    }
}
