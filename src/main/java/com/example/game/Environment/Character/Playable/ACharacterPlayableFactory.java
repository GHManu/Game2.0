package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.InputManager.InputManager;

public abstract class ACharacterPlayableFactory {
    public abstract ACharacterPlayable createPlayer(
            EWeaponType weaponType,
            EConcreteWeapon concreteWeapon,
            EProjectileType projectileType,
            EMovementType movementType,
            EConcreteMovement concreteMovement,
            InputManager inputManager
    );
}
