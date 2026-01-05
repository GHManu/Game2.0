package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.AWeaponFactory;

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
