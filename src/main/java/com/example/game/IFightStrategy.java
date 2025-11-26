package com.example.game;

public interface IFightStrategy {
    void attack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
}

