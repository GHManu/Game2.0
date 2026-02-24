package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.Attack.CommonAttackFireWeaponEnemy;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.AWeaponFactory;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.FireWeaponFactory;
import com.example.game.Environment.Character.Movement.NoInput.OneWayMovement;

public class EnemyFactory extends ACharacterEnemyFactory {

    @Override
    public ACharacterEnemy createEnemy(String weaponType, String concreteWeapon, String movementType, String concreteMovement) {
        ACharacterEnemy enemy = new Enemy();
        AWeaponFactory weapon_factory;

        switch (weaponType.replaceAll("\\s+", "").toLowerCase()){   //rimuove tutti gli spazi (anche multipli).
            case "fireweapon":
                weapon_factory = new FireWeaponFactory();
                AWeapon weapon_selected = weapon_factory.createWeapon(concreteWeapon);
                enemy.setWeapon(weapon_selected);
                enemy.setFightStrategyEnemy(new CommonAttackFireWeaponEnemy((AFireWeapon) enemy.getWeapon()));
                break;

            default:
                break;
        }
        switch (movementType.replaceAll("\\s+", "").toLowerCase()){
            case "withoutinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "oneway":
                        enemy.setMovementStrategy(new OneWayMovement());
                        break;
                }
                break;

            default:
                break;
        }

        return enemy;
    }
}
