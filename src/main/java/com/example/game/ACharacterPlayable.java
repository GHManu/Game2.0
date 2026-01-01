package com.example.game;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

import java.util.Set;



public abstract class ACharacterPlayable extends ACharacter {
    protected Group root;
    private double xDest, yDest;
    private ACharacterEnemy enemy;

    public ACharacterEnemy getEnemy() {
        return enemy;
    }

    public void setEnemy(ACharacterEnemy enemy) {
        this.enemy = enemy;
    }

    public double getxDest() {
        return xDest;
    }

    public void setxDest(double xDest) {
        this.xDest = xDest;
    }

    public double getyDest() {
        return yDest;
    }

    public void setyDest(double yDest) {
        this.yDest = yDest;
    }

    protected void setRoot(Group root){
        this.root = root;
    }

    protected abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);
    protected abstract void movement(double deltatime, Set<KeyCode> keyCodes);
}
