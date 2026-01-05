package com.example.game.Event;

import java.util.ArrayList;

public class EventBus implements IEventSubject {
    private volatile static EventBus unique_instance;
    private ArrayList<IEventListenerObserver> listeners;

    private EventBus() {
        this.listeners = new ArrayList<>();
    }

    public static void GetInstance(){
        if(unique_instance == null)
            synchronized (EventBus.class){
                if(unique_instance == null){
                    unique_instance = new EventBus();
                }
            }
    }

    public static EventBus get(){
        return unique_instance;
    }
    @Override
    public void addEventListenerObserver(IEventListenerObserver listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventListenerObserver(IEventListenerObserver listener) {
        listeners.remove(listener);
    }

    public void removeAll(){
        listeners.clear();
    }

    @Override
    public void notifyEventListenerObserver(DTOEvent dto) {
        for(IEventListenerObserver listener : listeners){
            listener.update(dto);
        }
    }
}
