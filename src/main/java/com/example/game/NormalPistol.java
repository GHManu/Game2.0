package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public class NormalPistol extends AFireWeapon {

    Projectile p;

    public Projectile getP() {
        return p;
    }

    public void setP(Projectile p) {
        this.p = p;
    }

    public NormalPistol(){

    }

    @Override
    void shot(double deltaTime, Player plr, Projectile p) {

    }

    @Override
    void movement(double deltaTime, Player plr, Projectile p) {

    }

    @Override
    public void attack(double deltatime, Player plr, boolean attack_flag, ProgressBar progressBar, Image attackImage) {
        if(attack_flag && plr.progressBar.getProgress() > 0.1 && progressBar.getProgress() > 0.1){
            attack_flag = false;
            p = new NormalProjectile(attackImage, this.x, this.y, plr.x, plr.y);
            Platform.runLater(() -> { plr.root.getChildren().addAll(p.cld.ret,p.imgView); });
            p = p;
            shot(deltatime, plr, p);
        }
    }
}
