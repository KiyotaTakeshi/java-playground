package com.kiyotakeshi.Collection;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        // 配列は長さが決まっており、要素の追加や削除がしづらい
        // 複数のデータが扱いやすい仕組みとして、コレクションがある
        // ・配列のように index を指定して値の取得や設定をしたい場合は List interface
        // ・要素の重複がなく、検索・ソートを高速に行いたい場合は Set interface
        // ・キーと値を分けて要素を扱いたい場合は Map interface

        // List
        // ArrayList は内部に配列を利用しているため
        // index を指定して要素を代入や取得は高速に行える
        // 末尾に要素を追加する処理も高速で行えるが、
        // 配列の途中に要素を追加しようとするとそれ以降のすべてを後ろにずらすため時間がかかる

        // LinkedList は要素自身が前後の要素の情報を持つ
        // index を指定した取得は先頭からリストを順番にたどるため時間がかかるが、
        // リストの途中に要素を高速に追加、削除できる
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

        // Map
        // HashMap
        // key からハッシュ値を計算して(key のオブジェクトの hashCode メソッドを使用)、
        // 内部のハッシュテーブルに key と value を格納する
        // key が異なっても hashCode がおなじになる場合は equals メソッドを用いて一致する要素を検索

        // LinkedHashMap は要素自身が前後の情報を持っている
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Mike", 88);
        scores.put("Popcorn", 68);
        scores.put("Taro", 60);
        System.out.println(scores);

        // get だと存在しない場合、値として null を持っている場合に null が返ってくるので
        // kye や value が存在しないことを確認するには contains を使う
        System.out.println(scores.containsKey("Mike")); // true
        System.out.println(scores.containsValue(60)); // true

        // static initializer で初期化した map を取得
        Map<Integer, String> map = Student.getMap();
        System.out.println(map.get(1));

        // Set
        // 値の集合を扱うインターフェースで、特定の要素を取得するメソッドは存在しない
        // Set の内部には Map が存在し、 Set に追加された要素は Map のキーとして保持される
        System.out.println("----------------");
        HashSet<String> names = new HashSet<>();
        names.add("Mike");
        names.add("Popcorn");
        names.add("Taro");
        names.add("Taro");
        System.out.println(names); // 重複は排除される

        // Queue
        // FIFO で値を出し入れするコレクション
        // 外部プログラムからデータが送信された、自分のプログラム側で処理が追いつかない場合に、
        // バッファとして使用し順番を保持したまま、後でデータを処理できる
        System.out.println("----------------");
        Queue<Integer> queue = new ArrayBlockingQueue<>(10);
        queue.offer(1);
        queue.offer(20);
        queue.offer(3);
        System.out.println(queue);
        System.out.println(queue.peek()); // 値を削除しない
        System.out.println(queue.poll());
        System.out.println(queue);

        // 双方向に値の出し入れができるコレクションが Deque(Double Ended Queue)
    }
}
