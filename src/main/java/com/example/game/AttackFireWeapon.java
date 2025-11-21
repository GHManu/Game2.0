package com.example.game;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class AttackFireWeapon implements IFightStrategy{

    @Override
    public void attack(double deltatime, ACharacterEnemy subject, ACharacterPlayable target) {

        AFireWeapon fireWeapon = ((AFireWeapon)subject.getWeapon());
        this.initialize_attack(deltatime, subject, target);

        //Player
        Collider target_cld = target.cld;
        Rectangle ret = target_cld.ret;
        ImageView target_img_view = target.imgView;
        ProgressBar target_prog_bar = target.progressBar;
        VBox target_vbox = target.vBox;
        ObservableList<Node> target_root_children = target.root.getChildren();

        //Enemy
        ProgressBar subject_prog_bar = subject.progressBar;

        //Projectile
        Collider projectile_cld = fireWeapon.p.cld;
        Rectangle projectile_cld_ret = projectile_cld.ret;


        if(subject_prog_bar.getProgress() > 0.1 && target_prog_bar.getProgress() > 0.1)    subject.getMovementStrategyWithoutInput().movement(deltatime, target, subject);

        if(subject_prog_bar.getProgress() > 0.1 && !subject.attack_flag && target_prog_bar.getProgress() > 0.1) {  //finchè è in vita

            fireWeapon.p =  fireWeapon.getMag().getFirst();
            subject.getWeapon().fight(deltatime);

            projectile_cld.collision_Detected(projectile_cld_ret, false);

            if (fireWeapon.p.isArrived(target.x, target.y)) {
                Platform.runLater(() -> { target_root_children.removeAll(projectile_cld_ret, fireWeapon.p.imgView); } );
                subject.attack_flag = true;
            }
            if(projectile_cld_ret.intersects(ret.getBoundsInLocal())){

                if(target_cld.dx && target.x > 0){
                    setPlrCollisionX(target_img_view, ret, target_prog_bar, target_vbox, -target.speed, subject.REBOUND);
                }

                if(target_cld.sx && target.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)){
                    setPlrCollisionX(target_img_view, ret, target_prog_bar, target_vbox, target.speed, subject.REBOUND);
                }

                if(target_cld.br && target.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)){
                    setPlrCollisionY(target_img_view, ret, target_prog_bar, target_vbox, target.speed, subject.REBOUND);
                }
                if(target_cld.fr && target.y >0){
                    setPlrCollisionY(target_img_view, ret, target_prog_bar, target_vbox, -target.speed, subject.REBOUND);
                }

                Platform.runLater(() -> { target_root_children.removeAll(projectile_cld_ret, fireWeapon.p.imgView); } );
                Platform.runLater(() -> {  target_prog_bar.setProgress(target_prog_bar.getProgress() - 0.2); });
                subject.attack_flag = true;
            }

        }


        if(subject_prog_bar.getProgress() < 0.1 && !fireWeapon.p.isArrived(target.x, target.y)){
            Platform.runLater(() -> { target_root_children.removeAll(projectile_cld_ret, fireWeapon.p.imgView); } );
        }

    }


    protected void initialize_attack(double deltatime, ACharacterEnemy subject,  ACharacterPlayable target) {
        if (subject.attack_flag && target.progressBar.getProgress() > 0.1 && subject.progressBar.getProgress() > 0.1) {

            subject.attack_flag = false;


            Projectile p = new NormalProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), subject.x, subject.y, target.x, target.y);
            Platform.runLater(() -> {
                target.root.getChildren().addAll(p.cld.ret, p.imgView);
            });
            ((AFireWeapon) subject.getWeapon()).getMag().set(0, p);

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
