package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    boolean attack_flag;
    final double REBOUND = 2.56784;

    IFightStrategy fightStrategy;

    public ACharacterEnemy(){

    }

    abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);

    public IFightStrategy getFightStrategy() {
        return fightStrategy;
    }

    public void setFightStrategy(IFightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }
}
