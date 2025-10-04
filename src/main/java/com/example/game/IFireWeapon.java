package com.example.game;

public interface IFireWeapon {
    abstract void shot(double deltaTime, Player plr, Projectile p);
}
