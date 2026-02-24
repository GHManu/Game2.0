package com.example.game.Environment.Character.Attack;

import com.example.game.Application.GameUpdate;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Map.MyMap;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategyPlayer;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.*;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.ProjectileCollisionResolver;
import com.example.game.UI.EGameImages;
import com.example.game.UI.HUD;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class CommonAttackFireWeaponPlayer extends ACommonAttack implements IFightStrategyPlayer {
    private final AFireWeapon fw;
    private ProjectileManager projectileManager = new ProjectileManager();
    private ProjectileCollisionResolver projectileCollisionResolver = new ProjectileCollisionResolver();

    public CommonAttackFireWeaponPlayer(AFireWeapon fw) {
        this.fw = fw;
    }

    private void removeProjectile(AProjectile p, ACharacterPlayable player, ProjectileIterator it) {
        it.remove();
        Platform.runLater( () -> {
                HUD.removeElement(player.root, p.getImgView());
        });
    }


    @Override
    public void normalAttack(double dt, ACharacterPlayable player) {
        initAttack(dt, player);
        ProjectileIterator it = new ProjectileIterator(fw.getMag());
        while (it.hasNext()) {
            NormalAProjectile p = (NormalAProjectile) it.next();
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

            ACharacterEnemy enemy = projectileCollisionResolver.hitEnemy(p, GameUpdate.getCharacters());
            if (enemy != null) {
                applyDamage(enemy, p.normal_damage);
                projectileManager.removeProjectile(player.root, p, it);
                continue;
            }
            for (ACharacter c : GameUpdate.getCharacters()) {
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

    @Override
    public void initAttack(double deltatime, ACharacterPlayable player) {
        if (player.getProgressBar().getProgress() <= 0) return;

        if (player.isInit_attack_flag()) {


            Bounds b = player.getImgView().localToScene(player.getImgView().getBoundsInLocal());
            double sceneX = b.getMinX() + b.getWidth() / 2;
            double sceneY = b.getMinY() + b.getHeight() / 2;

            Point2D local = player.root.sceneToLocal(sceneX, sceneY);
            double px = local.getX();
            double py = local.getY();

            double mxScene = player.getxDest();
            double myScene = player.getyDest();


            Point2D destLocal = player.root.sceneToLocal(mxScene, myScene);
            double mx = destLocal.getX();
            double my = destLocal.getY();


            NormalAProjectile p =  new NormalAProjectile(
                    EGameImages.ProvaAttacco.getImage(),
                    px,
                    py,
                    mx,
                    my
            );


            fw.getMag().add(p);
            HUD.addElement(player.root, p.getImgView());
            player.setAttack_flag(true);
        }

        player.setInit_attack_flag(false);
    }

}
