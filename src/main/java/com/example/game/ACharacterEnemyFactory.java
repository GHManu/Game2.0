package com.example.game;

public abstract class ACharacterEnemyFactory {
    abstract ACharacterEnemy createEnemy(ISubject subject,String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
