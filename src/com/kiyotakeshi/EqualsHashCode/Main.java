package com.kiyotakeshi.EqualsHashCode;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // オブジェクトの等価性を判定するには
        // 「オブジェクトが同じである(==)」と「オブジェクトの値が等しい(equals)」の2段階がある
        Integer test = 12345;
        Integer test2 = 12345;
        if (test == test2) {
            System.out.println("オブジェクトが同じです");
        } else {
            System.out.println("オブジェクトは違います");
        }
        if (test.equals(test2)) {
            System.out.println("オブジェクトの値は同じです");
        }

        // 自分で作成したクラスには、オブジェクトが等価であることを判定するために
        // equals メソッドをオーバライドして実装する必要がある
        Employee mike1 = new Employee(1, "mike");
        Employee mike_1 = new Employee(1, "mike");

        // HashSet は値の集合を扱うかクラスで同じ値を1つのみ持つ
        Set<Employee> employees = new HashSet<>();

        if (mike1.equals(mike_1)) {
            System.out.println("オブジェクトの値は同じです");
        }

        employees.add(mike1);
        employees.add(mike_1);

        // hashCode メソッドを実装していないと2件になる(hashCode が異なるため)
        // Employee が hashCode を override していない場合、
        // Object クラスのものが使われる
        // これはオブジェクトが異なると異なる値を返すため、
        // 重複を想定しない HashSet にフィールドの値が等しいオブジェクトが複数存在することになる
        System.out.println(employees.size()); // 1

        System.out.println("------------------");
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);

        // toString を override しているためオブジェクトの値も表示される
        System.out.println(point1);
        System.out.println(point2);

        System.out.println(point1.hashCode());
        System.out.println(point2.hashCode());

        // equals, hashCode を実装しているので等価であると判定される
        System.out.println(point1.equals(point2)); // true
    }
}
