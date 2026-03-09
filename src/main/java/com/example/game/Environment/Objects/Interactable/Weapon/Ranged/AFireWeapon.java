package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;


import java.util.List;

import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;

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
