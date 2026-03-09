package com.example.game.Environment.Objects.Interactable.Weapon;

import com.example.game.Environment.AEntity;

public abstract class AWeapon extends AEntity implements IFightWeaponStrategy {
    private double heaviness;
    public void setHeaviness(double heaviness) { this.heaviness = heaviness; }
    public double getHeaviness() { return heaviness; }

}
