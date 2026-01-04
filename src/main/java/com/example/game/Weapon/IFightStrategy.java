package com.example.game.Weapon;

import com.example.game.ACharacterEnemy;
import com.example.game.ACharacterPlayable;

public interface IFightStrategy {
    void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
    void normalAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
}

