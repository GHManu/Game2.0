package com.example.game;

public abstract class ACharacterEnemyFactory {  //factory method
    abstract ACharacterEnemy createEnemy(String weaponType, String concreteWeapon, String movementType, String concreteMovement);
}
