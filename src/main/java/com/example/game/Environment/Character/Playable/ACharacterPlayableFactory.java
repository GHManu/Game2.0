package com.example.game.Environment.Character.Playable;

import com.example.game.InputManager.InputManager;

public abstract class ACharacterPlayableFactory {
    public abstract ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement, InputManager input_manager);
}
