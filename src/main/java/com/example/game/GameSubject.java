package com.example.game;

import java.util.ArrayList;

public class GameSubject implements ISubject{

    private ArrayList<IObserver> observers;
    private AStatsObject stats_object;

    public GameSubject(){
        observers = new ArrayList<IObserver>();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        int i= observers.indexOf(observer);
        if (i>= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.update(stats_object);
        }
    }

    public void setStats_object(AStatsObject stats_object){
        this.stats_object = stats_object;
        notifyObservers();
    }
    public AStatsObject setStatsObject(){
        return this.stats_object;
    }
}
