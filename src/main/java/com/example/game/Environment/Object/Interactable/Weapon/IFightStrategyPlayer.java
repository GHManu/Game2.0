package com.example.game.Environment.Object.Interactable.Weapon;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

import java.util.List;

public interface IFightStrategyPlayer extends IFightStrategy {
    void initAttack(double deltatime, ACharacterPlayable player);
    void normalAttack(double deltatime, ACharacterPlayable player, List<ACharacter> characters);
}

