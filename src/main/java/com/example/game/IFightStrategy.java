package com.example.game;

public interface IFightStrategy {
    void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
    void normalAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
}

