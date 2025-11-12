package com.example.game;

import javafx.application.Platform;

import java.util.ArrayList;



public class NormalPistol extends AFireWeapon {

    public NormalPistol(){
        this.setProjectiles(new ArrayList<Projectile>());
        this.getProjectiles().add(new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(),0,0, 0, 0));
    }


    @Override
    public void fight(double deltatime) {
        this.shot(deltatime);

    }

    @Override
    void shot(double deltatime) {
        this.p.journey(deltatime, p.speed);
    }
}
