package com.example.game;


import java.util.List;

public abstract class AFireWeapon extends AWeapon{
    Projectile p;
    List<Projectile> mag;


    public AFireWeapon(){
        p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), 0,0,0,0);

    }

    public List<Projectile> getMag() {
        return mag;
    }

    public void setMag(List<Projectile> mag) {
        this.mag = mag;
    }

    abstract void shot(double deltatime);
}
