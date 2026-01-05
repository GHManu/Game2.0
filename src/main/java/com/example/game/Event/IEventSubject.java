package com.example.game.Event;

public interface IEventSubject {
    void addEventListenerObserver(IEventListenerObserver observer);
    void removeEventListenerObserver(IEventListenerObserver observer);
    void notifyEventListenerObserver(DTOEvent dto);
}
