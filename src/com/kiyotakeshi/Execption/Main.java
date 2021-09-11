package com.kiyotakeshi.Execption;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Optional;

public class Main {

    // log はクラス変数(クラスの static フィールドとして宣言する変数)
    // クラスロード時に生成され、クラスアンロード時に破棄される
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 例外は3種類
        // 1. java.lang.Exception (検査例外)
        // プログラム作成時に想定できる異常を通知するために利用
        // 想定するエラーに対する処理が存在することをコンパイル時にチェックできる
        // プログラムで catch して処理するか、
        // 上位の呼び出し元に対して例外を発生させる(throw)ことが必須
        // public List<String> readFile() throws IOException {

        // 2. java.lang.RuntimeException (実行時エラー)
        // プログラム作成時に想定されないエラーを通知するために利用
        // コンパイルエラーは発生しない
        // 呼び出し元に例外を補足させる必要なく、無条件で呼び出し元で発生
        // 発生したらそのスレッドが終了する(スレッドを開始した処理、 Java VM には例外は伝播しない)
        // メインスレッドである場合はアプリケーション自体が終了

        // 3. java.lang.Error
        // システムの動作を継続できない致命的なエラー
        // プログラムで補足するべきではない(アプリが異常状態に陥っており、終了させるべきなので)
        // OutOfMemoryError

        // エラーコードを return しない
        // エラーコードを決めてそれを return するのは、値の定義やメンテナンスが必要
        // Java には例外機構が備わっているので、エラーが発生したら例外を発生させる

        String strValue = "abc";
        try {
            // 動作を確認する際は、コメントインする
            // int intValue = Integer.valueOf(strValue);
            // System.out.println("intValue is " + intValue);
        } catch (NumberFormatException ex) {
            // エラーをもみ消さず(catch に何も書かない)、ログ出力する
            log.warn("not number!!!", ex);
            // main -> valueOf -> parseInt でエラーが発生したことが明白
            // 処理の始まり、根っこがどこかもわかる = Main.java:37
//            Sep 11, 2021 1:55:39 PM com.sun.org.slf4j.internal.Logger warn
//            WARNING: not number!!!
//                    java.lang.NumberFormatException: For input string: "abc"
//            at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//            at java.lang.Integer.parseInt(Integer.java:580)
//            at java.lang.Integer.valueOf(Integer.java:766)
//            at com.kiyotakeshi.Execption.Main.main(Main.java:37)
        }

        OptionalStack<String> optionalStack = new OptionalStack<>();

        Optional<String> optional = optionalStack.pop();
        // optional の値が存在しないので empty を返す
        System.out.println(optional.orElse("empty"));

        optionalStack.push("Java");
        optionalStack.push("Kotlin");
        optionalStack.push("Scala");

        optional = optionalStack.pop();
        if (optional.isPresent()) {
            System.out.println(optional.get()); // Scala
        }

        optional = optionalStack.pop();
        optional.ifPresent(System.out::println); // Kotlin

    }
}
