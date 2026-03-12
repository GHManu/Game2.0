package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;

import java.util.List;

public class MovementHandler {

        private final CollisionHandler collision_handler;

        public MovementHandler(CollisionHandler collision_handler) {
            this.collision_handler = collision_handler;
        }

        public void handle(double deltaTime, List<ACharacter> characters,
                           ACharacterEnemy enemy, ACharacterPlayable player) {
            collision_handler.handle(characters);
            enemy.getMovement_strategy().movement(deltaTime, enemy, characters);
            player.movement(deltaTime, characters);
        }

}
