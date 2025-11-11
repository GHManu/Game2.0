package com.example.game;

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
