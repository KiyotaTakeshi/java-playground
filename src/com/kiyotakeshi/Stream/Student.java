package com.kiyotakeshi.Stream;

import java.util.HashMap;
import java.util.Map;

public class Student {

    // name, score は、クラスのフィールドとして宣言するインスタンス変数
    // 親のインスタンスを同じライフサイクルで親オブジェクトの GC 時に一緒に破棄される
    private String name;
    private int score;

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
}
