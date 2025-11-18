package com.example.game;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

//16 righe

public abstract class ACharacterPlayable extends ACharacter {
    protected Group root;

    protected final void setRoot(Group root){
        this.root = root;
    }
    protected abstract void sprintStatus(double deltatime);
    protected abstract void walk(double deltaTime);

    protected abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);

    //protected abstract void normal_attack(double deltatime);

}
