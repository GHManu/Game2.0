package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.UI.EGameImages;

import java.util.ArrayList;



public class NormalPistol extends AFireWeapon {

    public NormalPistol(){
        this.setMag(new ArrayList<AProjectile>());
        this.getMag().add(new NormalAProjectile(EGameImages.ProvaAttaccoEnemy.getImage(),0,0, 0, 0));
    }


    @Override
    public void fight(double deltatime) {
        this.shot(deltatime);

    }

    @Override
    void shot(double deltatime) {
        this.getProjectile().journey(deltatime, getProjectile().speed);
    }

}
