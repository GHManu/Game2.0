package com.example.game.Environment.Character.Playable;

public abstract class ACharacterPlayableFactory {
    public abstract ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
