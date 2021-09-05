package com.kiyotakeshi.Generics;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StringStack stringStack = new StringStack();

        // 要素がないから NPE
        // String element = stringStack.pop();
        // element.equals("Java");

        stringStack.push("Java");
        stringStack.push("Kotlin");
        stringStack.push("Scala");

        String element = stringStack.pop();

        // 利用時に要素があるか null チェックが必要
        if (element != null){
            System.out.println(element);
        }

        System.out.println("-------------");
        GenericStack<String> genericStack = new GenericStack<>();
        // genericStack.push(123); // incompatible types: のエラー
        genericStack.push("Java");
        String genericElement = genericStack.pop();
        System.out.println(genericElement);

        GenericStack<Integer> genericStack2 = new GenericStack<>();
        genericStack2.push(123);
        // genericStack2.push("Kotlin");
        Integer genericElement2 = genericStack2.pop();
        System.out.println(genericElement2);

        System.out.println("-------------");
        ArrayList<String> strList = new ArrayList<>();
        strList.add("Java");
        strList.add("Kotlin");
        GenericStack<String> genericStack3 = GenericStackUtil.as(strList);

        System.out.println("-------------");
        NumberStack<Integer> intStack = new NumberStack<>();
        NumberStack<Long> longStack = new NumberStack<>();
        // String は Number の子クラスではないのでコンパイルエラー
        // java.lang.String is not within bounds of type-variable E
        // NumberStack<String> strStack = new NumberStack<>();

        intStack.push(100);
        intStack.push(200);
    }
}
