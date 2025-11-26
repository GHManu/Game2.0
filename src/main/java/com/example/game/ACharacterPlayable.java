package com.example.game;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

//16 righe

public abstract class ACharacterPlayable extends ACharacter {
    protected Group root;
    private double xDest, yDest;
    private boolean attack_flag;
    private IFightStrategy fightStrategy;


    public IFightStrategy getFightStrategy() {
        return fightStrategy;
    }

    public void setFightStrategy(IFightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }

    public boolean isAttack_flag() {
        return attack_flag;
    }

    public void setAttack_flag(boolean attack_flag) {
        this.attack_flag = attack_flag;
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

    protected final void setRoot(Group root){
        this.root = root;
    }
    protected abstract void sprintStatus(double deltatime);
    protected abstract void walk(double deltaTime);

    protected abstract void select_attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);

    //protected abstract void normal_attack(double deltatime);

}
