package com.example.game;

public abstract class AMeleeWeapon extends AWeapon implements IFightStrategy{
    abstract void slash(double deltaTime, Player plr);
}
