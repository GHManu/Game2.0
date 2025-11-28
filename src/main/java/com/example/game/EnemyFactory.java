package com.example.game;

public class EnemyFactory extends ACharacterEnemyFactory{

    @Override
    ACharacterEnemy createEnemy(String weaponType, String concreteWeapon, String movementType, String concreteMovement) {
        ACharacterEnemy enemy = new Enemy();
        AWeaponFactory weapon_factory;

        switch (weaponType.replaceAll("\\s+", "").toLowerCase()){   //rimuove tutti gli spazi (anche multipli).
            case "fireweapon":
                weapon_factory = new FireWeaponFactory();
                AWeapon weapon_selected = weapon_factory.createWeapon(concreteWeapon);
                enemy.setWeapon(weapon_selected);
                enemy.setFightStrategy(new AttackFireWeaponEnemy());
                break;

            default:
                break;
        }
        switch (movementType.replaceAll("\\s+", "").toLowerCase()){
            case "withoutinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "oneway":
                        enemy.setMovementStrategy(new OneWayMovementWithoutInput());
                        break;
                }
                break;

            default:
                break;
        }

        return enemy;
    }
}
