package com.example.game.Environment.Object.Interactable.Weapon.Ranged;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Collider;

import java.util.List;

public class ProjectileCollisionResolver {

    public boolean hitWall(AProjectile p, List<Collider> walls) {
        for (Collider wall : walls) {
            if (p.getCld().getShape().intersects(wall.getShape().getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }

    public ACharacterEnemy hitEnemy(AProjectile p, List<ACharacter> chars) {
        for (ACharacter c : chars) {
            if (c instanceof ACharacterEnemy enemy) {
                if (p.getCld().getShape().intersects(enemy.getCld().getShape().getBoundsInLocal())) {
                    return enemy;
                }
            }
        }
        return null;
    }

    public boolean hitEnemyProjectile(AProjectile p, AFireWeapon enemyWeapon) {
        ProjectileIterator it = new ProjectileIterator(enemyWeapon.getMag());
        while (it.hasNext()) {
            AProjectile ep = it.next();
            if (p.getCld().getShape().intersects(ep.getCld().getShape().getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }
}
