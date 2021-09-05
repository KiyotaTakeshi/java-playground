package com.kiyotakeshi.Generics;

import java.util.ArrayList;
import java.util.List;

public class StringStack {
    private List<String> taskList = new ArrayList<>();

    public StringStack() {
    }

    public boolean push(String task) {
        return taskList.add(task);
    }

    public String pop() {

        if (taskList.isEmpty()) {
            return null;
        }
        return taskList.remove(taskList.size() - 1);
    }
}
