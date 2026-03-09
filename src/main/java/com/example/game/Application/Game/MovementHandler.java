package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

import java.util.List;

public class MovementHandler {

        private final CollisionHandler collisionHandler;

        public MovementHandler(CollisionHandler collisionHandler) {
            this.collisionHandler = collisionHandler;
        }

        public void handle(double deltaTime, List<ACharacter> characters,
                           ACharacterEnemy enemy, ACharacterPlayable player) {
            collisionHandler.handle(characters);
            enemy.getMovementStrategy().movement(deltaTime, enemy, characters);
            player.movement(deltaTime, characters);
        }

}
