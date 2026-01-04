package com.example.game.Weapon.Ranged;

import com.example.game.Weapon.AWeapon;
import com.example.game.Weapon.AWeaponFactory;

public class FireWeaponFactory extends AWeaponFactory {


    @Override
    public AWeapon createWeapon(String type) {
        switch (type.toLowerCase()){
            case "pistol":
                return new NormalPistol();
            default:
                return null;
        }
    }
}
