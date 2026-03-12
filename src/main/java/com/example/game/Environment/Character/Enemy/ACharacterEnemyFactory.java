package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;

public abstract class ACharacterEnemyFactory {
    public abstract ACharacterEnemy createEnemy(EWeaponType weapon_type, EConcreteWeapon concrete_weapon, EProjectileType projectile_type,
                                                EMovementType movement_type, EConcreteMovement concrete_movement);
}
