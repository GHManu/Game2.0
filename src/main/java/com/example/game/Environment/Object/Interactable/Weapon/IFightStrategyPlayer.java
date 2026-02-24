package com.example.game.Environment.Object.Interactable.Weapon;

import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

public interface IFightStrategyPlayer extends IFightStrategy {
    void initAttack(double deltatime, ACharacterPlayable player);
    void normalAttack(double deltatime, ACharacterPlayable player);
}

