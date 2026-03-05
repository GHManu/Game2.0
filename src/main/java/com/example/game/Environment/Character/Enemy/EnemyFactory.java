package com.example.game.Environment.Character.Enemy;


import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.EProjectileType;


public class EnemyFactory extends ACharacterEnemyFactory {

    @Override
    public ACharacterEnemy createEnemy(EWeaponType weaponType, EConcreteWeapon concreteWeapon,
                                           EMovementType movementType, EConcreteMovement concreteMovement) {
        return new EnemyBuilder()
                .weaponType(weaponType)
                .concreteWeapon(concreteWeapon)
                .projectileType(EProjectileType.NORMAL)
                .movementType(movementType)
                .concreteMovement(concreteMovement)
                .build();
    }
}
