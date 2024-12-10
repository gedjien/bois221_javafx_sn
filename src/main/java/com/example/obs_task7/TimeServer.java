package com.example.obs_task7;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//Реализует интерфейс Subject.
//
//Содержит внутренний таймер, который отсчитывает секунды.
//
//Метод tick() увеличивает состояние времени и уведомляет всех наблюдателей.
//
//Методы: getState(), setState(int time), tick(), attach(IObserver observer), detach(IObserver observer), notifyAllObservers().

public class TimeServer implements Subject {
    private int timeState = 0;
    private Timer timer;
    private List<IObserver> observers = new ArrayList<>();

    public void start() {
        this.timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                tick();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void tick() {
        timeState++;
        notifyAllObservers();
    }

    public int getState() {
        return timeState;
    }

    public void setState(int time) {
        this.timeState = time;
        notifyAllObservers();
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }
}
