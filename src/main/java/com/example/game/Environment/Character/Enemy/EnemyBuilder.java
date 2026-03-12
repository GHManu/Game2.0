package com.example.game.Environment.Character.Enemy;

import com.example.game.Environment.Character.ACharacterBuilder;
import com.example.game.Environment.Character.Attack.CommonAttackFireWeaponEnemy;
import com.example.game.Environment.Objects.Interactable.Weapon.IFightStrategyEnemy;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.NormalProjectileFactory;

import java.util.Map;
import java.util.function.Function;


public class EnemyBuilder extends ACharacterBuilder<ACharacterEnemy> {

    private static final Map<EProjectileType, Function<AFireWeapon, IFightStrategyEnemy>> projectile_registry = Map.of(
            EProjectileType.NORMAL, fw -> new CommonAttackFireWeaponEnemy(fw, new NormalProjectileFactory())
    );

    @Override
    protected void applyFightStrategy(ACharacterEnemy enemy) {
        if (projectile_type == null) return;
        enemy.setFight_strategy_enemy(projectile_registry.get(projectile_type).apply((AFireWeapon) enemy.getWeapon()));
    }

    @Override
    public ACharacterEnemy build() {
        ACharacterEnemy enemy = new Enemy();
        applyWeapon(enemy);
        applyFightStrategy(enemy);
        applyMovement(enemy);
        return enemy;
    }
}