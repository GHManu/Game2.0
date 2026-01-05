package com.example.game.Environment.Character.Movement;

import com.example.game.Environment.Character.ACharacter;

public interface IMovementStrategy {
    void movement(double deltatime, ACharacter target);
}
