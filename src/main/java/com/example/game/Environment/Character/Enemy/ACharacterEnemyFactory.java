package com.example.game.Environment.Character.Enemy;

public abstract class ACharacterEnemyFactory {
    public abstract ACharacterEnemy createEnemy(String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
