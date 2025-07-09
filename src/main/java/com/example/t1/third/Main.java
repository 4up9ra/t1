package com.example.t1.third;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = MyExecutors.newFixedMyThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            pool.execute(() ->
                    System.out.println("Executing task " + taskNumber + " in thread: " + Thread.currentThread().getName()));
        }

        Thread.sleep(1000);
        pool.shutdown();

        pool.awaitTermination();

        System.out.println("All tasks completed, thread pool shutdown");
    }
}