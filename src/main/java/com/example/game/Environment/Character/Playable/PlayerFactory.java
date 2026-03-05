package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;

import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EWeaponType;

import com.example.game.Environment.Object.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.InputManager.InputManager;
public class PlayerFactory extends ACharacterPlayableFactory{

    @Override
    public ACharacterPlayable createPlayer(EWeaponType weaponType, EConcreteWeapon concreteWeapon,
                                           EMovementType movementType, EConcreteMovement concreteMovement, InputManager inputManager) {
        return new PlayerBuilder()
                .weaponType(weaponType)
                .concreteWeapon(concreteWeapon)
                .projectileType(EProjectileType.NORMAL)
                .movementType(movementType)
                .concreteMovement(concreteMovement)
                .inputManager(inputManager)
                .build();
    }
}
