package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Objects.Interactable.Weapon.IFightStrategyEnemy;

public abstract class ACharacterEnemy extends ACharacter {

    private final double REBOUND = 2.56784;
    private IFightStrategyEnemy fight_strategy_enemy;


    public IFightStrategyEnemy getFight_strategy_enemy() {
        return fight_strategy_enemy;
    }

    public void setFight_strategy_enemy(IFightStrategyEnemy fight_strategy_enemy) {
        this.fight_strategy_enemy = fight_strategy_enemy;
    }

    public abstract void selectAttack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);


    public double getREBOUND() {
        return REBOUND;
    }
}
