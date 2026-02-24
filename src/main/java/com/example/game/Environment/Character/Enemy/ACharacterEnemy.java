package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyEnemy;

public abstract class ACharacterEnemy extends ACharacter {

    private final double REBOUND = 2.56784;
    private IFightStrategyEnemy fightStrategyEnemy;


    public IFightStrategyEnemy getFightStrategyEnemy() {
        return fightStrategyEnemy;
    }

    public void setFightStrategyEnemy(IFightStrategyEnemy fightStrategyEnemy) {
        this.fightStrategyEnemy = fightStrategyEnemy;
    }

    public abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);


    public double getREBOUND() {
        return REBOUND;
    }
}
