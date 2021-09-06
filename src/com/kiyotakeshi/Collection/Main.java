package com.kiyotakeshi.Collection;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 配列は長さが決まっており、要素の追加や削除がしづらい
        // 複数のデータが扱いやすい仕組みとして、コレクションがある
        // ・配列のように index を指定して値の取得や設定をしたい場合は List interface
        // ・要素の重複がなく、検索・ソートを高速に行いたい場合は Set interface
        // ・キーと値を分けて要素を扱いたい場合は Map interface

        // List
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 38, 29, 24, 3));
        integerList.add(65);
        System.out.println("integerList:" + integerList);

        int threeIndex = integerList.indexOf(3);
        System.out.println("\'3\' place " + threeIndex);

        System.out.println("\'2\' contains " + integerList.contains(2));

        Collections.sort(integerList);
        System.out.println("sorted integerList:" + integerList);

        // binarySearch は事前に配列の要素をソートしておく必要がある
        // 順不同な配列に対して検索をするときは
        // ・何度も検索する場合はソートして binarySearch を行う
        // ・一度しか検索しないならソートせずに線形検索を行う
        System.out.println(Collections.binarySearch(integerList, 3));

        System.out.println("----------------");
        // ArrayList は内部に配列を利用しているため
        // index を指定して要素を代入や取得は高速に行える
        // 末尾に要素を追加する処理も高速で行えるが、
        // 配列の途中に要素を追加しようとするとそれ以降のすべてを後ろにずらすため時間がかかる

        // LinkedList は要素自身が前後の要素の情報を持つ
        // index を指定した取得は先頭からリストを順番にたどるため時間がかかるが、
        // リストの途中に要素を高速に追加、削除できる
        List<Student> students = new ArrayList<>();
        students.add(new Student("mike", 80));
        students.add(new Student("popcorn", 78));
        students.add(new Student("taro", 60));

        // iterator には要素を削除するメソッドがある
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getScore() < 70) {
                System.out.println("70点以下はリストから削除");
                it.remove();
            }
        }
        System.out.println(students);

        System.out.println("----------------");
    }
}
