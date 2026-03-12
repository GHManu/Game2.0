package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.ACharacterBuilder;
import com.example.game.Environment.Character.Attack.CommonAttackFireWeaponPlayer;
import com.example.game.Environment.Objects.Interactable.Weapon.IFightStrategyPlayer;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.NormalProjectileFactory;

import java.util.Map;
import java.util.function.Function;




    public class PlayerBuilder extends ACharacterBuilder<ACharacterPlayable> {

        private static final Map<EProjectileType, Function<AFireWeapon, IFightStrategyPlayer>> projectile_registry = Map.of(
                EProjectileType.NORMAL, fw -> new CommonAttackFireWeaponPlayer(fw, new NormalProjectileFactory())
        );

        @Override
        protected void applyFightStrategy(ACharacterPlayable player) {
            if (projectile_type == null) return;
            player.setFight_strategy_player(projectile_registry.get(projectile_type).apply((AFireWeapon) player.getWeapon()));
        }

        @Override
        public ACharacterPlayable build() {
            ACharacterPlayable player = new Player();
            applyWeapon(player);
            applyFightStrategy(player);
            applyMovement(player);
            return player;
        }
    }
