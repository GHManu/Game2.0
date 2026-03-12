package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.InputManager.InputManager;

public abstract class ACharacterPlayableFactory {
    public abstract ACharacterPlayable createPlayer(
            EWeaponType weapon_type,
            EConcreteWeapon concrete_weapon,
            EProjectileType projectile_type,
            EMovementType movement_type,
            EConcreteMovement concrete_movement,
            InputManager input_manager
    );
}
