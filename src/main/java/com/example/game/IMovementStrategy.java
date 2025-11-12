package com.example.game;

public interface IMovementStrategy {
    void movement(double deltatime, ACharacterPlayable target, ACharacter subject);
}
