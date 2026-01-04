package com.example.game.Weapon.Ranged;


import com.example.game.UI.EGameImages;
import com.example.game.Weapon.AWeapon;

import java.util.List;

public abstract class AFireWeapon extends AWeapon {
    private AProjectile p;
    List<AProjectile> mag;


    public AFireWeapon(){
        p = new NormalAProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), 0,0,0,0);

    }

    public AProjectile getProjectile() {
        return p;
    }

    public void setProjectile(AProjectile p) {
        this.p = p;
    }

    public List<AProjectile> getMag() {
        return mag;
    }

    public void setMag(List<AProjectile> mag) {
        this.mag = mag;
    }

    abstract void shot(double deltatime);
}
