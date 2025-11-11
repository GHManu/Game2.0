package com.example.game;

public class EnemyFactory implements ICharacterEnemyFactory {


    @Override
    public ACharacterEnemy createEnemy(String enemyType) {
        switch(enemyType.toLowerCase().trim()){
            case "type1":
                return new EnemyType1();
            default:
                return null;
        }
    }
}
