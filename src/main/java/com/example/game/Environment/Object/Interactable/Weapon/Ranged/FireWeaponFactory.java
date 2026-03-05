package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.AWeaponFactory;
import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;

public class FireWeaponFactory extends AWeaponFactory {


    @Override
    public AWeapon createWeapon(EConcreteWeapon type) {
        switch (type){
            case NORMAL_PISTOL:
                return new NormalPistol();
            default:
                return null;
        }
    }
}
