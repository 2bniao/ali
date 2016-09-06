package com.mt.test.puling;

class Task1 implements Runnable {

    public void run() {
        System.out.println(System.currentTimeMillis() + "===" + Thread.currentThread().getName());
    }

}