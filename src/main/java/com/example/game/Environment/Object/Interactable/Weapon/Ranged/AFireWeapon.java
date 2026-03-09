package com.example.game.Environment.Object.Interactable.Weapon.Ranged;


import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;

import java.util.List;

public abstract class AFireWeapon extends AWeapon {
    private AProjectile p;
    List<AProjectile> mag;

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
