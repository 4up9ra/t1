package com.example.t1.third;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {

    private final List<MyThread> myThreads;
    private final LinkedList<Runnable> taskQueue;
    private volatile boolean isShutdown;

    public MyThreadPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be > 0");
        }
        this.myThreads = new ArrayList<>();
        this.taskQueue = new LinkedList<>();

        for (int i = 0; i < poolSize; i++) {
            MyThread myThread = new MyThread();
            myThread.setName("MyThread-" + i);
            myThreads.add(myThread);
            myThread.start();
        }
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            if (isShutdown) {
                throw new IllegalStateException("MyThreadPool is shutdown, cannot add new tasks");
            }
            taskQueue.addLast(task);
            taskQueue.notifyAll();
        }
    }

    public void shutdown() {
        synchronized (taskQueue) {
            isShutdown = true;
            taskQueue.notifyAll();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (MyThread myThread : myThreads) {
            myThread.join();
        }
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                while (true) {
                    Runnable task;
                    synchronized (taskQueue) {
                        while (taskQueue.isEmpty() && !isShutdown) {
                            taskQueue.wait();
                        }
                        if (isShutdown && taskQueue.isEmpty()) {
                            break;
                        }
                        task = taskQueue.removeFirst();
                    }
                    try {
                        task.run();
                    } catch (RuntimeException e) {
                        System.out.println("Exception occurred while processing task in thread: " + Thread.currentThread().getName());
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}