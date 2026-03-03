package com.example.game.Application;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.InputManager.InputManager;

import java.util.List;

public class AttackHandler {
        private final InputManager inputManager;

        public AttackHandler(InputManager inputManager) {
            this.inputManager = inputManager;
        }

        public void handle(double deltaTime, ACharacterPlayable player, ACharacterEnemy enemy, List<ACharacter> characters) {
            if (inputManager.consumeMouseClick()) {
                player.setInit_attack_flag(true);
                player.setxDest(inputManager.getMouseX());
                player.setyDest(inputManager.getMouseY());
            }
            if (player.isAttack_flag() || player.isInit_attack_flag())
                player.select_attack(deltaTime, player, enemy, characters);
        }
}
