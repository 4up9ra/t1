package com.example.t1.second;

import java.util.List;

public class DataGenerator {

    public static List<Integer> generateNumbers() {
        return List.of( 5, 2, 10, 9, 4, 3, 10, 1, 13);
    }

    public static List<Employee> generateEmployees() {
        Employee e1 = new Employee("Иван1", 10, "Инженер");
        Employee e2 = new Employee("Иван2", 20, "Инженер");
        Employee e3 = new Employee("Иван3", 30, "Директор");
        Employee e4 = new Employee("Иван4", 40, "Инженер");
        Employee e5 = new Employee("Иван5", 50, "Инженер");
        Employee e6 = new Employee("Иван6", 60, "Инженер");
        Employee e7 = new Employee("Иван7", 70, "Бухгалтер");
        Employee e8 = new Employee("Иван8", 80, "Инженер");
        Employee e9 = new Employee("Иван9", 90, "Механик");
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static List<String> generateWordsList() {
        return List.of("aaaaaa", "waaaaa", "ba", "ha", "caa", "xaaa", "zaaaa", "a");
    }

    public static String generateString() {
        return "a a a a b f d s c f g g d s  s  s s    s ss t y";
    }

    public static String[] generateWordsArray() {
        return new String[]{"aa a aa", "bbb bb abbbba", "ccccccc ac acccca"};
    }
}