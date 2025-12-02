package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    private final double REBOUND = 2.56784;


    public ACharacterEnemy(){

    }

    abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);


    public double getREBOUND() {
        return REBOUND;
    }
}
