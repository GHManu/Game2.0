package com.example.game;

public class EnemyFactory implements ICharacterFactory{

    private final ACharacter enemy = new Enemy();

    @Override
    public ACharacter createWeapon(String weaponType,String weapon) {
        AWeaponFactory weaponFactory;
        switch (weaponType.toLowerCase().trim()){
            case "fireweapon":
                weaponFactory = new FireWeaponFactory();
                switch(weapon.toLowerCase().trim()){
                    case "pistol":
                        enemy.setWeapon(weaponFactory.createWeapon("pistol"));
                        break;
                }
                return enemy;
            default:
                weaponFactory = new FireWeaponFactory();
                enemy.setWeapon(weaponFactory.createWeapon("pistol"));
                return enemy;

        }
    }
}
