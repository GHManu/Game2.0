package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Enemy.ACharacterEnemyFactory;
import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Character.Playable.ACharacterPlayableFactory;
import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.EProjectileType;
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
                    EWeaponType.FIRE_WEAPON, EConcreteWeapon.NORMAL_PISTOL, EProjectileType.NORMAL ,EMovementType.WITH_INPUT, EConcreteMovement.SIX_WAY, inputManager);
            characters.add(player);
            return player;
        }

        public ACharacterEnemy spawnEnemy() {
            ACharacterEnemy enemy = enemyFactory.createEnemy(
                    EWeaponType.FIRE_WEAPON, EConcreteWeapon.NORMAL_PISTOL, EProjectileType.NORMAL, EMovementType.WITHOUT_INPUT, EConcreteMovement.ONE_WAY);
            characters.add(enemy);
            return enemy;
        }

        public List<ACharacter> getCharacters() {
            return Collections.unmodifiableList(characters);
        }
}
