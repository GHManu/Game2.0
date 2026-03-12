package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.InputManager.InputManager;

import java.util.List;

public class AttackHandler {
        private final InputManager input_manager;

        public AttackHandler(InputManager input_manager) {
            this.input_manager = input_manager;
        }

        public void handle(double deltaTime, ACharacterPlayable player, ACharacterEnemy enemy, List<ACharacter> characters) {
            if (input_manager.consumeMouseClick()) {
                player.setInit_attack_flag(true);
                player.setX_dest(input_manager.getMouseX());
                player.setY_dest(input_manager.getMouseY());
            }
            if (player.isAttack_flag() || player.isInit_attack_flag())
                player.selectAttack(deltaTime, player, enemy, characters);
        }
}
