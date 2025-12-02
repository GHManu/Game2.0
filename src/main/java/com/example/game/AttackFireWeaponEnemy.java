package com.example.game;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class AttackFireWeaponEnemy implements IFightStrategy{


    @Override
    public void normalAttack(double deltatime, ACharacterEnemy subject, ACharacterPlayable target) {

        AFireWeapon fireWeapon = ((AFireWeapon)subject.getWeapon());
        this.initAttack(deltatime, subject, target);

        Collider target_cld = target.getCld();
        Rectangle ret = target_cld.ret;
        ImageView target_img_view = target.getImgView();
        ProgressBar target_prog_bar = target.getProgressBar();
        VBox target_vbox = target.getvBox();
        ObservableList<Node> target_root_children = target.root.getChildren();


        ProgressBar subject_prog_bar = subject.getProgressBar();


        Collider projectile_cld = fireWeapon.p.getCld();
        Rectangle projectile_cld_ret = projectile_cld.ret;


        if(subject_prog_bar.getProgress() > 0.1 && target_prog_bar.getProgress() > 0.1)    subject.getMovementStrategy().movement(deltatime, subject);

        if(subject_prog_bar.getProgress() > 0.1 && !subject.attack_flag && target_prog_bar.getProgress() > 0.1) {  //finchè è in vita

            fireWeapon.p =  fireWeapon.getMag().getFirst();
            subject.getWeapon().fight(deltatime);

            projectile_cld.collision_Detected(projectile_cld_ret, false);

            if (fireWeapon.p.isArrived(target.getX(), target.getY())) {
                Platform.runLater(() -> { target_root_children.remove( fireWeapon.p.getImgView()); } );
                subject.attack_flag = true;
            }
            if(projectile_cld_ret.intersects(ret.getBoundsInLocal())){

                if(target_cld.dx && target.getX() > 0){
                    setPlrCollisionX(target_img_view, ret, target_prog_bar, target_vbox, -target.getSpeed(), subject.getREBOUND());
                }

                if(target_cld.sx && target.getX() < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)){
                    setPlrCollisionX(target_img_view, ret, target_prog_bar, target_vbox, target.getSpeed(), subject.getREBOUND());
                }

                if(target_cld.br && target.getY() < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)){
                    setPlrCollisionY(target_img_view, ret, target_prog_bar, target_vbox, target.getSpeed(), subject.getREBOUND());
                }
                if(target_cld.fr && target.getY() >0){
                    setPlrCollisionY(target_img_view, ret, target_prog_bar, target_vbox, -target.getSpeed(), subject.getREBOUND());
                }

                Platform.runLater(() -> { target_root_children.removeAll(projectile_cld_ret, fireWeapon.p.getImgView()); } );
                Platform.runLater(() -> {  target_prog_bar.setProgress(target_prog_bar.getProgress() - 0.2); });
                subject.attack_flag = true;
            }

        }


        if(subject_prog_bar.getProgress() < 0.1 && !fireWeapon.p.isArrived(target.getX(), target.getY())){
            Platform.runLater(() -> { target_root_children.removeAll(projectile_cld_ret, fireWeapon.p.getImgView()); } );
        }

    }
    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if (enemy.attack_flag && player.getProgressBar().getProgress() > 0.1 && enemy.getProgressBar().getProgress() > 0.1) {

            enemy.attack_flag = false;


            Projectile p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), enemy.getX(), enemy.getY(), player.getX(), player.getY());
            Platform.runLater(() -> {
                player.root.getChildren().add(p.getImgView());
            });
            ((AFireWeapon) enemy.getWeapon()).getMag().set(0, p);

        }


    }

    private void setPlrCollisionY(ImageView imageView, Rectangle ret, ProgressBar progbar, VBox vBox, double speed, double enemyrebound){
        Platform.runLater(() -> {
            imageView.setY(imageView.getY() + (speed * enemyrebound));
            ret.setLayoutY(ret.getLayoutY() - (speed * enemyrebound));
            progbar.setTranslateY(progbar.getTranslateY() + (speed * enemyrebound* 0.4));
            vBox.setTranslateY(vBox.getTranslateY() + (speed * enemyrebound* 0.4));
        });
    }
    private void setPlrCollisionX(ImageView imageView, Rectangle ret, ProgressBar progbar, VBox vBox, double speed, double enemyrebound){
        Platform.runLater(() -> {
            imageView.setX(imageView.getX() + (speed * enemyrebound));
            ret.setLayoutX(ret.getLayoutX() - (speed * enemyrebound));
            progbar.setTranslateX(progbar.getTranslateX() + (speed * enemyrebound* 0.4));
            vBox.setTranslateX(vBox.getTranslateX() + (speed * enemyrebound* 0.4));
        });
    }
}
