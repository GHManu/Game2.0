package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EWeaponType;

public abstract class ACharacterEnemyFactory {
    public abstract ACharacterEnemy createEnemy(EWeaponType weaponType, EConcreteWeapon concreteWeapon,
                                                EMovementType movementType, EConcreteMovement concreteMovement);
}
