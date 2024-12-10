package com.example.obs_task7;

//Определяет методы, которые должны реализовывать классы-наблюдатели.
//
//Методы: notifyAllObservers(), attach(IObserver obs), detach(IObserver obs).

public interface Subject {
    void notifyAllObservers();
    void attach(IObserver obs);
    void detach(IObserver obs);
}
