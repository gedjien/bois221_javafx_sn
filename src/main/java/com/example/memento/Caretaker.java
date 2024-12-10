package com.example.memento;

import java.util.ArrayDeque;
import java.util.Queue;

public class Caretaker {
    private Queue<Memento> mementoList = new ArrayDeque<>();

    public void push(Memento state) {
        mementoList.add(state);
    }

    public Memento pop() {
        return mementoList.poll();
    }
}
