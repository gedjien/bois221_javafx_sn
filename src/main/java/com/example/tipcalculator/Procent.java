package com.example.tipcalculator;

public class Procent {
    private float sum;

    public Procent(float sum) {
        this.sum = sum;
    }

    public float countPr(int pr) {
        return sum * pr / 100.0f;
    }

    public float countSum(float sum, float pr) {
        return sum + pr;
    }

    public int countSumRnd(int pr) {
        return Math.round(sum + countPr(pr));
    }
}
