package com.example.game;

public abstract class ACharacterPlayableFactory {
    abstract ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
