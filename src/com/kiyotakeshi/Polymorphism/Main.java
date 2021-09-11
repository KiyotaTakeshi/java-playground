package com.kiyotakeshi.Polymorphism;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // ポリモフィズム(polymorphism) とは、同じ型やメソッドを記述して異なる動作を行えるようにすること

        // 以下はどちらとも List interface の add メソッドを呼び出しているが、
        // 前者は ArrayList, 後者は LinkedList のメソッドが実行される
        // 内部的には異なる処理が実行されるが、プログラマは動作の違いを意識しなくていい
        // (list 変数の中になんのインスタンスが入っているか)
        // ArrayList, LinkedList クラスのどちらも List interface を実装しているため可能
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        System.out.println(list1);

        List<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        System.out.println(list1);
    }
}
