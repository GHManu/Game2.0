package com.example.game;


public abstract class AFireWeapon extends AWeapon {
    abstract void shot(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy);
    abstract void movement(double deltaTime, ACharacterPlayable plr, Projectile p, ACharacterEnemy enemy);
}
