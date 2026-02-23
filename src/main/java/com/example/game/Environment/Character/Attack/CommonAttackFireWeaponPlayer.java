package com.example.game.Environment.Character.Attack;

import com.example.game.Application.GameUpdate;
import com.example.game.Environment.AEntity;
import com.example.game.Environment.Character.Enemy.ACharacterEnemy;
import com.example.game.Environment.Character.Playable.ACharacterPlayable;
import com.example.game.Environment.Collider;
import com.example.game.Environment.Map.MyMap;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AProjectile;
import com.example.game.UI.EGameImages;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.ProjectileIterator;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.NormalAProjectile;
import com.example.game.UI.HUD;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class CommonAttackFireWeaponPlayer extends ACommonAttack {
    private final AFireWeapon fw;

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
    public void normalAttack(double dt, ACharacterEnemy enm, ACharacterPlayable player) {
        initAttack(dt,enm, player);
        ProjectileIterator it = new ProjectileIterator(fw.getMag());
        while (it.hasNext()) {
            NormalAProjectile p = (NormalAProjectile) it.next();
            fw.setProjectile(p);
            player.getWeapon().fight(dt);
            if (p.isArrived(player.getxDest(), player.getyDest())) {
                removeProjectile(p, player, it);
                continue;
            }

            for (Collider wall : MyMap.getWallColliders()) {
                if (p.getCld().getShape().intersects(
                        wall.getShape().getBoundsInLocal())) {
                    removeProjectile(p, player, it);
                    break;
                }
            }


            for (AEntity c : GameUpdate.getCharacters()) {
                if (c instanceof ACharacterEnemy enemy) {
                    if (p.getCld().getShape().intersects(enemy.getCld().getShape().getBoundsInLocal())) {
                        applyDamage(enemy, p.normal_damage);
                        removeProjectile(p, player, it);
                        break;
                    }

                    AFireWeapon enemyWeapon = (AFireWeapon) enemy.getWeapon();
                    ProjectileIterator enemyIt = new ProjectileIterator(enemyWeapon.getMag());
                    while (enemyIt.hasNext()) {
                        AProjectile ep = enemyIt.next();
                        if (p.getCld().getShape().intersects(ep.getCld().getShape().getBoundsInLocal())) {
                            removeProjectile(p, player, it);
                            break;
                        }
                    }

                }
            }
        }
        if (fw.getMag().isEmpty()) player.setAttack_flag(false);
    }

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
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
