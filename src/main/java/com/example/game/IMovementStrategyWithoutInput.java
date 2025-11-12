package com.example.game;

public interface IMovementStrategyWithoutInput{
    void movement(double deltatime, ACharacterPlayable target, ACharacter subject);
}
