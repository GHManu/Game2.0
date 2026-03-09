package com.example.game.Environment.Character.Attack;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Map.MyMap;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyPlayer;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.*;
import com.example.game.UI.EGameImages;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.util.List;

public class CommonAttackFireWeaponPlayer extends ACommonAttack implements IFightStrategyPlayer {
    private final AFireWeapon fw;
    private final ProjectileManager projectileManager = new ProjectileManager();
    private final ProjectileCollisionResolver projectileCollisionResolver = new ProjectileCollisionResolver();
    private final IProjectileFactory projectileFactory;

    public CommonAttackFireWeaponPlayer(AFireWeapon fw, IProjectileFactory projectileFactor) {
        this.fw = fw;
        this.projectileFactory = projectileFactor;
    }


    @Override
    public void normalAttack(double dt, ACharacterPlayable player, List<ACharacter> characters) {
        initAttack(dt, player);
        ProjectileIterator it = new ProjectileIterator(fw.getMag());
        while (it.hasNext()) {
            AProjectile p =  it.next();
            fw.setProjectile(p);
            player.getWeapon().fight(dt);
            if (p.isArrived(player.getxDest(), player.getyDest())) {
                this.projectileManager.removeProjectile(player.root, p, it);
                continue;
            }

            if (projectileCollisionResolver.hitWall(p, MyMap.getWallColliders())) {
                projectileManager.removeProjectile(player.root, p, it);
                continue;
            }

            ACharacterEnemy enemy = projectileCollisionResolver.hitEnemy(p, characters);
            if (enemy != null) {
                applyDamage(enemy, p.normal_damage);
                projectileManager.removeProjectile(player.root, p, it);
                continue;
            }
            for (ACharacter c : characters) {
                if (c instanceof ACharacterEnemy e) {
                    AFireWeapon enemyWeapon = (AFireWeapon) e.getWeapon();
                    if (projectileCollisionResolver.hitEnemyProjectile(p, enemyWeapon)) {
                        projectileManager.removeProjectile(player.root, p, it);
                        break;
                    }
                }
            }

        }
        if (fw.getMag().isEmpty()) player.setAttack_flag(false);
    }

    private Point2D getPlayerCenter(ACharacterPlayable player) {
        Bounds b = player.getImgView().localToScene(player.getImgView().getBoundsInLocal());
        double sceneX = b.getMinX() + b.getWidth() / 2;
        double sceneY = b.getMinY() + b.getHeight() / 2;
        return player.root.sceneToLocal(sceneX, sceneY);
    }

    private Point2D getDestination(ACharacterPlayable player) {
        return player.root.sceneToLocal(player.getxDest(), player.getyDest());
    }

   
   @Override
    public void initAttack(double deltatime, ACharacterPlayable player) {
        if (player.getProgressBar().getProgress() <= 0) return;

        if (player.isInit_attack_flag()) {
            Point2D origin = getPlayerCenter(player);
            Point2D dest   = getDestination(player);
            
            AProjectile p = projectileFactory.create(
                origin.getX(), origin.getY(),
                dest.getX(), dest.getY(),
                EGameImages.ProvaAttacco.getImage()
            );
            fw.getMag().add(p);
            projectileManager.spawnProjectile(player.root, p);
            player.setAttack_flag(true);
        }

        player.setInit_attack_flag(false);
    }
}
