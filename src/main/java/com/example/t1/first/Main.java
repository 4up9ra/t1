package com.example.t1.first;

public class Main {

    public static void main(String[] args) {
        try {
            TestRunner.runTests(MyClass.class);
        } catch (Exception e) {
            System.out.print("Exception thrown: " + e.getMessage());
        }
    }
}
