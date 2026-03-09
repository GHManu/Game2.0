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

        private static final Map<EProjectileType, Function<AFireWeapon, IFightStrategyPlayer>> PROJECTILE_REGISTRY = Map.of(
                EProjectileType.NORMAL, fw -> new CommonAttackFireWeaponPlayer(fw, new NormalProjectileFactory())
        );

        @Override
        protected void applyFightStrategy(ACharacterPlayable player) {
            if (projectileType == null) return;
            player.setFightStrategyPlayer(PROJECTILE_REGISTRY.get(projectileType).apply((AFireWeapon) player.getWeapon()));
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
