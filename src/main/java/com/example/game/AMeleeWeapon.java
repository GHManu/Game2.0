package com.example.game;

public abstract class AMeleeWeapon extends AWeapon{
    abstract void slash(double deltaTime, PlayerFireWeaponType plr);
}
