package com.example.game;

public class EnemyFactory extends ACharacterEnemyFactory{

    @Override
    ACharacterEnemy createEnemy(String weaponType) {
        ACharacterEnemy enemy = new Enemy();
        

        return enemy;
    }
}
