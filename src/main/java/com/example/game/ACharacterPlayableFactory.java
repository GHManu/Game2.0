package com.example.game;

public abstract class ACharacterPlayableFactory {
    public abstract ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
