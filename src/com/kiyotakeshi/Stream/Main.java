package com.kiyotakeshi.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("popcorn", 78));
        students.add(new Student("taro", 60));
        students.add(new Student("mike", 80));

        students.stream() // stream instance を作成
                .filter(s -> s.getScore() >= 70) // 中間操作(stream から stream を作成)
                .filter(s -> s.getName().contains("m")) // 中間操作
                .forEach(s -> System.out.println(s.getName())); // 終端操作

        // 終端操作で stream からコレクションや配列に変換したり、要素ごとに処理したり、要素を集計する
        // Stream API は How(個別の処理)ではなく What(処理の目的)を列挙するするコード

        // lambda は処理の内容を式として代入する記法
        // sort は引数に Comparator の実装をとる
        // default void sort(Comparator<? super E> c) {
        students.sort((Student o1, Student o2) ->
                // Comparator の実装
                Integer.compare(o2.getScore(), o1.getScore()));
        System.out.println(students);

        // メソッド参照により、すでに用意されているメソッドそのものを代入できる
        // 代入先の関数型インターフェースの引数の数と型が一致していれば、そこにメソッドを代入可能
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.forEach(System.out::println);

        System.out.println("---------------------");
        List<Student> students2 = new ArrayList<>();
        students2.add(new Student("popcorn", 78));
        students2.add(new Student("taro", 60));
        students2.add(new Student("mike", 80));

        // map メソッドは要素を別の値に書き換える中間処理
        // Student オブジェクトから score の値を取り出すことで、 Stream の型が Integer に変わる
        // 実際は Stream インスタンスを変数に代入することはない
        // // Stream<Student> stream = students2.stream();
        // Stream<Integer> integerStream = students2.stream().map(s -> s.getScore());
        // integerStream.forEach(System.out::println);
        students2.stream()
                .map(Student::getScore)
                // 終端で Stream に対して繰り返し処理を行いたい場合は forEach
                // void forEach(Consumer<? super T> action);
                .forEach(System.out::println);

        System.out.println("---------------------");
        List<Group> groups = new ArrayList<>();

        Group group1 = new Group();
        group1.add(new Student("Taro", 59));
        group1.add(new Student("Jiro", 70));
        group1.add(new Student("Saburo", 83));
        groups.add(group1);

        Group group2 = new Group();
        group2.add(new Student("Mike", 69));
        group2.add(new Student("Popcorn", 40));
        group2.add(new Student("Yamada", 98));
        groups.add(group2);

        // このあとの処理単位は List<Student>
        // Stream<List<Student>> listStream = groups.stream().map(g -> g.getStudents());

        // flatMap は stream を結合して1つの stream として扱えるようにする
        // この後は Student に対して処理できる
        // Stream<Student> studentStream = groups.stream().flatMap(g -> g.getStudents().stream());

        // 全ての Student を score 順で並べる
        // map メソッドのみを使う
        List<Student> allStudents = new ArrayList<>();
        groups.stream().forEach(g -> allStudents.addAll(g.getStudents()));
        allStudents.stream()
                // sorted は2つの引数を受け付けて int を返す関数オブジェクトを指定する
                // Comparator は戻り値の int によって挙動が変わる
                // 0 より小さい場合は第1引数、大きい場合は第2引数のオブジェクトが前方になる
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));

        System.out.println("---------------------");
        // flatMap を使う(より簡潔に書ける)
        groups.stream()
                .flatMap(g -> g.getStudents().stream())
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                // 昇順だと以下の記載が可能
                // .sorted((s1, s2) -> s1.getScore() - s2.getScore())
                // .sorted(Comparator.comparingInt(Student::getScore))
                .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));

        System.out.println("---------------------");
        List<Student> students3 = new ArrayList<>();
        students3.add(new Student("popcorn", 78));
        students3.add(new Student("taro", 60));
        students3.add(new Student("mike", 80));

        // 要素を絞り込む中間操作は、要素の型自体は変わらない
        students3.stream()
                .filter(s -> s.getScore() > 70)
                // Stream<T> filter(Predicate<? super T> predicate);
                .limit(1)
                .forEach(s -> System.out.println(s.getName()));

        System.out.println("---------------------");
        List<Student> students4 = new ArrayList<>();
        students4.add(new Student("taro", 78));
        students4.add(new Student("taro", 98));
        students4.stream()
                .map(Student::getName)
                .distinct() // ユニークに絞り込む
                .forEach(System.out::println);

        System.out.println("---------------------");
        // 結果をまとめて取り出す終端操作
        List<String> list1 = Arrays.asList("Taro", "Ichiro", "Saburo", "Ichiro");
//        List<String> newList1 =
        Set<String> collect1 = list1.stream()
                .filter(p -> p.length() > 5)
                // 要素を走査し、結果を作成する
                // .collect(Collectors.toList());
                .collect(Collectors.toSet());
        System.out.println(collect1);

        System.out.println("---------------------");
        List<Student> students5 = new ArrayList<>();
        students5.add(new Student("popcorn", 78));
        students5.add(new Student("taro", 60));
        students5.add(new Student("mike", 100));
        students5.add(new Student("john", 100));

        // 要素をグループ分けする
        Map<Integer, List<Student>> map5 = students5.stream()
                .collect(Collectors.groupingBy(Student::getScore));

        // System.out.println(map5);
        // {100=[Student{name='mike', score=100}, Student{name='john', score=100}], 60=[Student{name='taro', score=60}], 78=[Student{name='popcorn', score=78}]}
        List<Student> perfects = map5.get(100);
        perfects.forEach(s -> System.out.println(s.getName()));

        System.out.println("---------------------");
        // map, filter, collect が利用頻度が高い
        List<String> list3 = Arrays.asList("Mike", "Popcorn", "Tanaka");
        List<String> collect3 = list3.stream()
                .filter(p -> p.length() > 5)
                .map(p -> "[" + p + "]")
                .collect(Collectors.toList());

        // System.out.println(collect3); // [[Popcorn], [Tanaka]]
        collect3.forEach(System.out::println);

        System.out.println("---------------------");
        int count = 5;
        // IntStream は数値の配列から Stream をつくる
        String query = IntStream.range(0, count)
                .mapToObj(i -> "?")
                .collect(Collectors.joining(", "));
        System.out.println(query); // ?, ?, ?, ?, ?

        System.out.println("---------------------");
        // Stream API ではない、 List, Map に対して Java8 で追加されたメソッド
        ArrayList<String> list4 = new ArrayList<>();
        list4.add("Mike");
        list4.add("Popcorn");
        list4.add("Tanaka");

        // removeIf の引数は Predicate(戻り値が boolean)
        // public boolean removeIf(Predicate<? super E> filter) {
        list4.removeIf(s -> s.length() <= 5); // 5文字以下を削除
        list4.replaceAll(s -> s.toUpperCase()); // リストの要素を大文字に変更
        list4.forEach(System.out::println);

        System.out.println("---------------------");
        // 名前のリストを名前の長さで分類し、長さを key として名前のリストの Map を作る
        ArrayList<String> list5 = new ArrayList<>();
        list5.add("Mike");
        list5.add("Popcorn");
        list5.add("Tanaka");
        list5.add("Kendrick");
        list5.add("Yamada");
        HashMap<Integer, List<String>> nameLengthMap = new HashMap<>();
        list5.forEach(name -> {
            Integer nameLength = name.length();
//            // 名前の長さを key とした List があるか確認
//            List<String> valueList = nameLengthMap.get(nameLength);
//            if (valueList == null) {
//                valueList = new ArrayList<>();
//                nameLengthMap.put(nameLength, valueList);
//            }
//            // 存在する場合は要素を追加
//            valueList.add(name);

            // computeIfAbsent は key が無いときだけ関数が処理される
            // 空の List を値として与える
            List<String> valueList = nameLengthMap
                    .computeIfAbsent(nameLength, key -> new ArrayList<>());

            valueList.add(name);
        });
        System.out.println(nameLengthMap);
        // {4=[Mike], 6=[Tanaka, Yamada], 7=[Popcorn], 8=[Kendrick]}

        System.out.println("---------------------");
        // IntStream.range で作成した Stream を
        // boxed メソッドで変換し、それを List にする
        List<Integer> collect4 = IntStream.range(1, 100).boxed()
                .collect(Collectors.toList());
        System.out.println(collect4);
    }
}
