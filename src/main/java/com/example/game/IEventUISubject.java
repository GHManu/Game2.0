package com.example.game;

public interface IEventUISubject {
    void addEventListenerObserver(IEventListenerUIObserver observer);
    void removeEventListenerObserver(IEventListenerUIObserver observer);
    void notifyEventListenerObserver(DTOUIEvent dto);
}
