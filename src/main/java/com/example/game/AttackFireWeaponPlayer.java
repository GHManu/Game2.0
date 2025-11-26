package com.example.game;

import javafx.application.Platform;

public class AttackFireWeaponPlayer implements IFightStrategy{

    @Override
    public void attack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        AFireWeapon fireWeapon = (AFireWeapon) player.getWeapon();
        AFireWeapon fireWeapon_enemy = (AFireWeapon) enemy.getWeapon();
        ProjectileIterator projectileIterator = new ProjectileIterator(fireWeapon.getMag());

        while(projectileIterator.hasNext()){
            NormalProjectile p = (NormalProjectile) projectileIterator.next();
            fireWeapon.p = p;
            player.getWeapon().fight(deltatime);

            if(p.isArrived(player.getxDest(), player.getyDest())){

                Platform.runLater(() -> {
                    player.root.getChildren().remove( p.getImgView());
                });

                projectileIterator.remove();

            }else if (enemy.getCld().ret != null) {
                if ( p.getCld().ret.intersects(enemy.getCld().ret.getBoundsInLocal())) {
                    if (enemy.getHealth() > 1) {
                        double speed = enemy.getSpeed(), health = enemy.getHealth();
                        speed += 0.2;
                        enemy.setSpeed(speed);
                        health -= (enemy.getInitial_Health() * p.normal_damage);
                        enemy.setHealth(health);

                        Platform.runLater(() -> {
                            enemy.getProgressBar().setProgress(enemy.getProgressBar().getProgress() - p.normal_damage);
                        });
                    }

                    Platform.runLater(() -> {
                        player.root.getChildren().remove(p.getImgView());
                    });

                    projectileIterator.remove();
                    //DA MODIFICARE
                } else if(p.getCld().ret.intersects( fireWeapon_enemy.getMag().getFirst().getCld().ret.getBoundsInLocal() ) ){
                    Platform.runLater(() -> {
                        player.root.getChildren().remove(p.getImgView());
                    });

                    projectileIterator.remove();
                }
            }
        }

        if(fireWeapon.getMag().isEmpty())   player.setAttack_flag(false);

    }
}
