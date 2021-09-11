package com.kiyotakeshi.Execption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalStack<E> {

    private List<E> taskList = new ArrayList<>();

    public boolean push(E task) {
        return taskList.add(task);
    }

    public Optional<E> pop() {
        if (taskList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(taskList.remove(taskList.size() - 1));
    }
}
