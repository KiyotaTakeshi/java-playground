package com.kiyotakeshi.FileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // java.io.File の抱える問題
        // メタデータやシンボリックリンクを扱えない
        // ディレクトリ配下のファイルの生成、削除、更新を監視できない
        // File file = new File("src/com/kiyotakeshi/sample.app");
        // System.out.println(file);

        // Java7 で導入された java.nio.file.Paths はファイルパスを操作する強力なメソッドを用意
        Path path1 = Paths.get("src/com/kiyotakeshi/sample.app");
        System.out.println(path1);
        System.out.println(path1.getParent()); // 親ディレクトリの取得
        URI uri = path1.toUri();
        System.out.println(uri); // file:///Users/t.kiyota/utils/java-playground/src/com/kiyotakeshi/sample.app

        System.out.println("----------------------------");
        // ファイルの読み込み時にはストリームを用いて、
        // データをひとまとめの塊でなく流れとして扱う
        // 巨大なファイルの処理でそれを全てメモリに展開してシステムを壊さないように、
        // 先頭から少しづつデータを読み出して処理する

        // Java7 以降は try-with-resources 構文でストリームを記述する
        // Path path2 = Paths.get("/Users/t.kiyota/utils/java-playground/src/com/kiyotakeshi/sample.app");
        // binary の読み取り
        Path path2 = Paths.get("src/com/kiyotakeshi/sample.app");
        try (InputStream is = Files.newInputStream(path2)) {
            // InputStream interface の read メソッドは
            // ファイルの最後に達した時 -1 を返す
            for (int ch; (ch = is.read()) != -1; ) {
                System.out.print((char) ch); // this is sample
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        // テキストファイルの読み取り
        System.out.println("\n----------------------------");
        Path path3 = Paths.get("src/com/kiyotakeshi/sample.txt");
        try (BufferedReader reader = Files.newBufferedReader(path3, StandardCharsets.UTF_8)) {
//            for (String line; (line = reader.readLine()) != null; ) {
//                System.out.println(line);
//            }
            // Stream API を使ってファイル操作が可能
            reader.lines()
                    .map(s -> s.split(" ")[0]) // 1つ目の塊のみ
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------");
        // テキストファイルの書き込み
        Path path4 = Paths.get("src/com/kiyotakeshi/write_sample.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path4, StandardCharsets.UTF_8)) {
            writer.append("this is");
            writer.newLine();
            writer.append("write test");
        } catch (IOException e) {
            System.err.println(e);
        }

        // コピー
        Path fromFile = Paths.get("src/com/kiyotakeshi/sample.txt");
        Path toFile = Paths.get("src/com/kiyotakeshi/copy_sample.txt");

        try {
            Files.copy(fromFile, toFile);
        } catch (FileAlreadyExistsException e) {
            System.out.println("コピー後のファイルがすでに存在しています");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 作成
        Path path5 = Paths.get("src/com/kiyotakeshi/new_sample.txt");
        try {
            Files.createFile(path5);
        } catch (FileAlreadyExistsException e) {
            System.out.println("すでに存在しています");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 一時ファイル
        Path path6 = Paths.get("src/com/kiyotakeshi/");
        Path tempPath = null;
        try {
            tempPath = Files.createTempFile(path6, "test_", ".tmp");
            System.out.println(tempPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 削除
        try {
            Files.deleteIfExists(toFile);
            // boolean deleted = Files.deleteIfExists(toFile);
            Files.deleteIfExists(path5);
            Files.deleteIfExists(tempPath);
        } catch (NoSuchFileException e) {
            System.out.println("対象が存在しない");
            e.printStackTrace();
        } catch (DirectoryNotEmptyException e) {
            System.out.println("対象がディレクトリで空ではない");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
