package com.example.game;

public interface ICharacterPlayableFactory {
    ACharacterPlayable createPlayer(String playerType);
}
