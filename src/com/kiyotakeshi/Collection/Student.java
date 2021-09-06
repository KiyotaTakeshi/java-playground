package com.kiyotakeshi.Collection;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int score;

    // クラスの初期化時に Map も初期化する static initializer
    private static final Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1,"hoge");
        map.put(2,"fuga");
        map.put(3,"hogefuga");
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static Map<Integer, String> getMap() {
        return map;
    }
}
