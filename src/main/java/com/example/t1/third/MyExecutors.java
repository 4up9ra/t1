package com.example.t1.third;

public class MyExecutors {

    public static MyThreadPool newFixedMyThreadPool(int poolSize) {
        return new MyThreadPool(poolSize);
    }
}