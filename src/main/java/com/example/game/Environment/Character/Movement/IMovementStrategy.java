package com.example.game.Environment.Character.Movement;

import com.example.game.Environment.Character.ACharacter;

import java.util.List;


public interface IMovementStrategy {
    void movement(double deltatime, ACharacter target , List<ACharacter> characters);
}
