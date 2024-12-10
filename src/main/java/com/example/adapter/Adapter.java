package com.example.adapter;

import java.util.ArrayList;
import java.util.Arrays;

public class Adapter {
    private OrdArray array;

    public Adapter() {
        array = new OrdArray(1000);
    }

    public ArrayList<String> display() {
        String[] subStr;
        String delimeter = " ";
        subStr = array.display().split(delimeter);
        return new ArrayList<>(Arrays.asList(subStr));
    }

    public void insert(long value) {
        array.insert(value);
    }

    public boolean delete(long value) {
        return array.delete(value);
    }

    public int find(long value) {
        return array.find(value);
    }
}
