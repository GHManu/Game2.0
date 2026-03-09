package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;

public abstract class ACharacterEnemyFactory {
    public abstract ACharacterEnemy createEnemy(EWeaponType weaponType, EConcreteWeapon concreteWeapon, EProjectileType projectileType,
                                                EMovementType movementType, EConcreteMovement concreteMovement);
}
