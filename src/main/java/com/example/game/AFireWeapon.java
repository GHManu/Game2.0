package com.example.game;

public abstract class AFireWeapon extends AWeapon implements IFightStrategy{
    abstract void shot(double deltaTime, Player plr, Projectile p);
}
