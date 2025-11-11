package com.example.game;

public interface ICharacterEnemyFactory {
    //da aggiungere anche movimento e attacco, così quando creo l'enemy decido
    ACharacterEnemy createEnemy(String enemyType);



}
