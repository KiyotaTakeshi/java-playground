package com.kiyotakeshi.Generics;

import java.util.ArrayList;
import java.util.List;

// 仮型パラメータに制限を加えることで指定可能な型を絞り込む
// これにより extends で絞り込んだクラスのメソッドが呼べる
public class NumberStack<E extends Number> {
    private List<E> taskList = new ArrayList<>();

    public NumberStack() {
    }

    public boolean push(E task) {
        // Number クラスのメソッドである .intValue が呼べる
        System.out.println("Added " + task.intValue());
        return taskList.add(task);
    }

    public E pop() {
        if (taskList.isEmpty()) {
            return null;
        }
        return taskList.remove(taskList.size() - 1);
    }
}
