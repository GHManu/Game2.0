package com.example.game;

public interface ICharacterListenerObservable {
    void addObserver(ICharacterListenerObserver observer);
    void removeObserver(ICharacterListenerObserver observer);
}
