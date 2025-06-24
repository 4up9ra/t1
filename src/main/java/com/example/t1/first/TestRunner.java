package com.example.t1.first;

import com.example.t1.first.annotation.AfterSuite;
import com.example.t1.first.annotation.BeforeSuite;
import com.example.t1.first.annotation.Test;
import com.example.t1.first.exception.IllegalAnnotationArgumentException;
import com.example.t1.first.exception.IncorrectAnnotationCountException;
import com.example.t1.first.exception.IncorrectModifierTypeException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRunner {

    public static <T> void runTests(Class<T> clazz) throws Exception {
        Object object = clazz.getDeclaredConstructor().newInstance();
        Map<Integer, List<Method>> instanceMethods = new HashMap<>();
        Map<String, Method> staticMethods = new HashMap<>();
        collectMethods(clazz, instanceMethods, staticMethods);
        invokeMethods(object, instanceMethods, staticMethods);
    }

    private static void invokeMethods(Object object, Map<Integer, List<Method>> instanceMethods, Map<String, Method> staticMethods) throws Exception {
        invokeStaticMethod(BeforeSuite.class.getSimpleName(), object, staticMethods);
        invokeCommonMethods(object, instanceMethods);
        invokeStaticMethod(AfterSuite.class.getSimpleName(), object, staticMethods);
    }

    private static void invokeCommonMethods(Object object, Map<Integer, List<Method>> instanceMethods) throws Exception {
        List<Integer> keys = getSortedPriorityList(instanceMethods.keySet());
        for (Integer key : keys) {
            for (Method method : instanceMethods.get(key)) {
                method.invoke(object);
            }
        }
    }

    private static void invokeStaticMethod(String simpleName, Object object, Map<String, Method> staticMethods) throws Exception {
        Method method = staticMethods.get(simpleName);
        if (method != null) {
            method.invoke(object);
        }
    }

    private static List<Integer> getSortedPriorityList(Set<Integer> keySet) {
        List<Integer> keys = new ArrayList<>(keySet);
        keys.sort(Collections.reverseOrder());
        return keys;
    }

    private static <T> void collectMethods(Class<T> clazz, Map<Integer, List<Method>> instanceMethods, Map<String, Method> staticMethods) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                collectStaticMethods(BeforeSuite.class, method, staticMethods);
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                collectStaticMethods(AfterSuite.class, method, staticMethods);
            } else if (method.isAnnotationPresent(Test.class)) {
                collectInstanceMethods(Test.class, method, instanceMethods);
            }
        }
    }

    private static <T> void collectStaticMethods(Class<T> annotationClass, Method method, Map<String, Method> staticMethods) {
        checkIfStatic(method.getModifiers(), annotationClass.getSimpleName());
        if (staticMethods.containsKey(annotationClass.getSimpleName())) {
            throw new IncorrectAnnotationCountException("Only one method can be marked by annotation '" + annotationClass.getSimpleName() + "'");
        }
        staticMethods.put(annotationClass.getSimpleName(), method);
    }

    private static void checkIfStatic(int modifiers, String annotationName) {
        if (!Modifier.isStatic(modifiers)) {
            throw new IncorrectModifierTypeException("Modifier must be 'static' for '" + annotationName + "' annotated method");
        }
    }

    private static void collectInstanceMethods(Class<Test> annotationClass, Method method, Map<Integer, List<Method>> instanceMethods) {
        checkPriority(Test.class, method);
        instanceMethods.computeIfAbsent(method.getAnnotation(annotationClass).priority(), k -> new ArrayList<>()).add(method);
    }

    private static void checkPriority(Class<Test> annotationClass, Method method) {
        if (method.getAnnotation(annotationClass).priority() < 1 || method.getAnnotation(annotationClass).priority() > 10) {
            throw new IllegalAnnotationArgumentException("Parameter 'priority' of '" + annotationClass.getSimpleName() + "' annotation must be between 1 and 10");
        }
    }
}
