package com.example.t1.first;

import com.example.t1.first.annotation.AfterSuite;
import com.example.t1.first.annotation.BeforeSuite;
import com.example.t1.first.annotation.Test;

public class MyClass {

    @BeforeSuite
    public static void doSomethingBeforeSuite() {
        System.out.println("Doing something before suite");
    }

    @Test
    public void doSomething() {
        System.out.println("I'm doing something");
    }

    @Test(priority = 10)
    public void doSomethingVeryImportant() {
        System.out.println("I'm doing something very important!");

    }

    @Test(priority = 1)
    public void doSomethingNotImportant() {
        System.out.println("I'm doing something not important");
    }

    @Test(priority = 7)
    public void doSomethingNotSoImportant() {
        System.out.println("I'm doing something not so important");

    }

    @AfterSuite
    public static void doSomethingAfterSuite() {
        System.out.println("Doing something after suite");
    }
}
