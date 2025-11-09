package com.example.game;


import java.util.List;

public abstract class AFireWeapon extends AWeapon{
    List<Projectile> projectiles;

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    abstract void shot(double deltatime, Projectile p, ACharacterPlayable player, ACharacterEnemy enemy);
}
