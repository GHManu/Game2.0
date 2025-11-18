package com.example.game;

public class EnemyFactory extends ACharacterEnemyFactory{

    @Override
    ACharacterEnemy createEnemy(String weaponType, String concreteWeapon, String movementType, String concreteMovement) {
        ACharacterEnemy enemy = new Enemy();
        AWeaponFactory weapon_factory;
        switch (weaponType.trim().toLowerCase()){
            case "fireweapon":
                weapon_factory = new FireWeaponFactory();
                AWeapon weapon_selected = weapon_factory.createWeapon(concreteWeapon);
                enemy.setWeapon(weapon_selected);
                break;

            default:
                break;
        }
        switch (movementType.trim().toLowerCase()){
            case "withoutinput":
                switch(concreteMovement.trim().toLowerCase()){
                    case "oneway":
                        enemy.setMovementStrategyWithoutInput(new OneWayMovementWithoutInput());
                        break;
                }
                break;

            default:
                break;
        }

        return enemy;
    }
}
