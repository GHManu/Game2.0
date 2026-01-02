package com.example.game;

public interface IEventSubject {
    void addEventListenerObserver(IEventListenerObserver observer);
    void removeEventListenerObserver(IEventListenerObserver observer);
    void notifyEventListenerObserver(DTOEvent dto);
}
