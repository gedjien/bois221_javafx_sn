package com.example.strategy;

public class CurrentContext {
    private SortingStrategy strategy;

    public CurrentContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setSortingMethod(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public SortingStrategy getStrategy() {
        return strategy;
    }

    public void sortNumbers(int[] numbers) {
        strategy.sort(numbers);
    }
}
