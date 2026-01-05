package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import javafx.scene.Group;




public abstract class ACharacterPlayable extends ACharacter {
    public Group root;
    private double xDest, yDest;


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

    public void setRoot(Group root){
        this.root = root;
    }

    public abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);
    public abstract void movement(double deltatime);
}
