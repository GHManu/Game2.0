package com.example.game;

import java.util.ArrayList;

public class EventUIBus implements IEventUISubject {
    private static EventUIBus unique_instance;
    private ArrayList<IEventListenerUIObserver> listeners;

    private EventUIBus() {
        this.listeners = new ArrayList<>();
    }

    public static void GetInstance(){
        if(unique_instance == null)
            synchronized (EventUIBus.class){
                if(unique_instance == null){
                    unique_instance = new EventUIBus();
                }
            }
    }

    public static EventUIBus get(){
        return unique_instance;
    }
    @Override
    public void addEventListenerObserver(IEventListenerUIObserver listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventListenerObserver(IEventListenerUIObserver listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyEventListenerObserver(DTOUIEvent dto) {
        for(IEventListenerUIObserver listener : listeners){
            listener.update(dto);
        }
    }
}
