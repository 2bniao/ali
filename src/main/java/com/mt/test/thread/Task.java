package com.mt.test.thread;

public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("this is " + name + "==" + Thread.currentThread().getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
