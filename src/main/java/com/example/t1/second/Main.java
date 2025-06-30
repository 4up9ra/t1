package com.example.t1.second;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.t1.second.DataGenerator.generateEmployees;
import static com.example.t1.second.DataGenerator.generateNumbers;
import static com.example.t1.second.DataGenerator.generateString;
import static com.example.t1.second.DataGenerator.generateWordsArray;
import static com.example.t1.second.DataGenerator.generateWordsList;

public class Main {

    public static void main(String[] args) {
        System.out.println(findThirdBiggestNumber(generateNumbers()));
        System.out.println(findThirdBiggestUniqueNumber(generateNumbers()));
        System.out.println(getThreeOldestEmployeesNamesByPosition(generateEmployees(), "Инженер"));
        System.out.println(countAverageEmployeesAgeByPosition(generateEmployees(), "Инженер"));
        System.out.println(findLongestWord(generateWordsList()));
        System.out.println(getMapByWordCount(generateString()));
        printWordsInLengthOrder(generateWordsList());
        System.out.println(getLongestWord(generateWordsArray()));
    }

    private static int findThirdBiggestNumber(List<Integer> numbers) {
        return numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    private static int findThirdBiggestUniqueNumber(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    private static List<String> getThreeOldestEmployeesNamesByPosition(List<Employee> employees, String position) {
        return employees.stream()
                .filter(e -> e.getPosition().equals(position))
                .sorted(Comparator.comparingInt(Employee::getAge)
                        .reversed())
                .limit(3)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    private static double countAverageEmployeesAgeByPosition(List<Employee> employees, String position) {
        return employees.stream()
                .filter(e -> e.getPosition().equals(position))
                .mapToInt(Employee::getAge)
                .average()
                .orElseThrow();
    }

    private static String findLongestWord(List<String> words) {
        return words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }

    private static Map<String, Long> getMapByWordCount(String s) {
        return Arrays.stream(s.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void printWordsInLengthOrder(List<String> words) {
        System.out.println(
                words.stream()
                        .sorted(Comparator.comparingInt(String::length)
                                .thenComparing(Comparator.naturalOrder()))
                        .toList()
        );
    }

    private static String getLongestWord(String[] wordsArray) {
        return Arrays.stream(wordsArray)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }
}