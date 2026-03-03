package com.example.game.Application;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Enemy.ACharacterEnemyFactory;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Character.Playable.ACharacterPlayableFactory;
import com.example.game.InputManager.InputManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterSpawner {

        private final ACharacterPlayableFactory playerFactory;
        private final ACharacterEnemyFactory enemyFactory;
        private final List<ACharacter> characters = new ArrayList<>(); // non più static

        public CharacterSpawner(ACharacterPlayableFactory playerFactory,
                                ACharacterEnemyFactory enemyFactory) {
            this.playerFactory = playerFactory;
            this.enemyFactory = enemyFactory;
        }

        public ACharacterPlayable spawnPlayer(InputManager inputManager) {
            ACharacterPlayable player = playerFactory.createPlayer(
                    "fire weapon", "pistol", "with input", "sixway", inputManager);
            characters.add(player);
            return player;
        }

        public ACharacterEnemy spawnEnemy() {
            ACharacterEnemy enemy = enemyFactory.createEnemy(
                    "fire weapon", "pistol", "without input", "oneway");
            characters.add(enemy);
            return enemy;
        }

        public List<ACharacter> getCharacters() {
            return Collections.unmodifiableList(characters);
        }
}
