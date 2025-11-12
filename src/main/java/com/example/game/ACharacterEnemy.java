package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    boolean attack_flag;
    final double REBOUND = 2.56784;



    public ACharacterEnemy(){

    }

    abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);


}
