package com.kiyotakeshi.Generics;

import java.util.List;

public class GenericStackUtil {

    // メソッドに generics を使う場合は戻り値の前に仮型パラメータを指定
    public static <T> GenericStack<T> as(List<T> list) {

        GenericStack<T> genericStack = new GenericStack<>();

        // メソッド参照
        list.forEach(genericStack::push);
        return genericStack;
    }
}
