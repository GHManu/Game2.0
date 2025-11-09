package com.example.game;


public interface IFightStrategy {
    void fight(double deltatime, ACharacterPlayable player, ACharacterEnemy enemy);
}
