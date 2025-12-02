package com.example.game;

import javafx.application.Platform;

public class AttackFireWeaponPlayer implements IFightStrategy{



    @Override
    public void normalAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {

        this.initAttack(deltatime, enemy, player);

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

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if(player.getProgressBar().getProgress() > 5.551115123125783E-17) {
            if(player.isInit_attack_flag()){
                if(player.getWeapon() instanceof AFireWeapon fireWeapon) {
                    NormalProjectile p = new NormalProjectile(EGameImages.ProvaAttacco.getImage(), player.getImgView().getLayoutX(), player.getImgView().getLayoutY(), player.getxDest(), player.getyDest());

                    player.setAttack_flag(true);


                    fireWeapon.getMag().add(p);
                    Platform.runLater(() -> {
                        player.root.getChildren().add(p.getImgView());
                    });
                }
            }
            player.setInit_attack_flag(false);

        }
    }


}
