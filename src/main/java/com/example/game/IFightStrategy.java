package com.example.game;


public interface IFightStrategy {
    void attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);
}
