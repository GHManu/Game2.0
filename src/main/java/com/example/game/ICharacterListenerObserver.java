package com.example.game;

import com.example.game.Environment.Character.ACharacter;

public interface ICharacterListenerObserver {
    void onDeath(ACharacter c);
    void onDamage(ACharacter c, double amount);
}
