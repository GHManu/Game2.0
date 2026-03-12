package com.example.game.Application.Game;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Enemy.ACharacterEnemyFactory;
import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Character.Playable.ACharacterPlayableFactory;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.InputManager.InputManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterSpawner {

        private final ACharacterPlayableFactory player_factory;
        private final ACharacterEnemyFactory enemy_factory;
        private final List<ACharacter> characters = new ArrayList<>(); // non più static

        public CharacterSpawner(ACharacterPlayableFactory player_factory,
                                ACharacterEnemyFactory enemy_factory) {
            this.player_factory = player_factory;
            this.enemy_factory = enemy_factory;
        }

        public ACharacterPlayable spawnPlayer(InputManager inputManager) {
            ACharacterPlayable player = player_factory.createPlayer(
                    EWeaponType.FIRE_WEAPON, EConcreteWeapon.NORMAL_PISTOL, EProjectileType.NORMAL ,EMovementType.WITH_INPUT, EConcreteMovement.SIX_WAY, inputManager);
            characters.add(player);
            return player;
        }

        public ACharacterEnemy spawnEnemy() {
            ACharacterEnemy enemy = enemy_factory.createEnemy(
                    EWeaponType.FIRE_WEAPON, EConcreteWeapon.NORMAL_PISTOL, EProjectileType.NORMAL, EMovementType.WITHOUT_INPUT, EConcreteMovement.ONE_WAY);
            characters.add(enemy);
            return enemy;
        }

        public List<ACharacter> getCharacters() {
            return Collections.unmodifiableList(characters);
        }
}
