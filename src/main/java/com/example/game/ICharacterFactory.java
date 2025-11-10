package com.example.game;

public interface ICharacterFactory {

    ACharacter createEnemyFireWeaponType(AWeapon weapon);
    ACharacter createPlayerFireWeaponType(AWeapon weapon);

}
