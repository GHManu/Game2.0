package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

public abstract class ACharacterEnemy extends ACharacter {

    private final double REBOUND = 2.56784;


    public ACharacterEnemy(){

    }

    public abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);


    public double getREBOUND() {
        return REBOUND;
    }
}
