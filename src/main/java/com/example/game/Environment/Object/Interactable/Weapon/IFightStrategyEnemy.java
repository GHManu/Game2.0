package com.example.game.Environment.Object.Interactable.Weapon;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

public interface IFightStrategyEnemy extends IFightStrategy{
    void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
    void normalAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player);
}
