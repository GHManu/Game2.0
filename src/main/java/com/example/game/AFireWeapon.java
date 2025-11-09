package com.example.game;


import java.util.List;

public abstract class AFireWeapon extends AWeapon{
    Projectile p;
    List<Projectile> projectiles;


    public AFireWeapon(){
        p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), 0,0,0,0);

    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    abstract void shot(double deltatime, Projectile p, ACharacterPlayable player, ACharacterEnemy enemy);
}
