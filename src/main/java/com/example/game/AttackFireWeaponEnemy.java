package com.example.game;

import com.example.game.UI.EGameImages;
import com.example.game.UI.UIDTO;
import com.example.game.Weapon.IFightStrategy;
import com.example.game.Weapon.Ranged.AFireWeapon;
import com.example.game.Weapon.Ranged.AProjectile;
import com.example.game.Weapon.Ranged.NormalAProjectile;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class AttackFireWeaponEnemy implements IFightStrategy {


    private void moveEnemyIfAlive(double dt, ACharacterEnemy subject, ACharacterPlayable target) {
        if (subject.getProgressBar().getProgress() > 0.1 &&
                target.getProgressBar().getProgress() > 0.1) {
            subject.getMovementStrategy().movement(dt, subject);
        }
    }
    private void updateProjectile(double dt, ACharacterEnemy subject, ACharacterPlayable target, AFireWeapon fireWeapon) {

        fireWeapon.setProjectile(fireWeapon.getMag().getFirst());
        subject.getWeapon().fight(dt);

        Collider projectileCld = fireWeapon.getProjectile().getCld();
        projectileCld.collisionDetected(projectileCld.getShape(), false);
    }
    private void removeProjectile(Group root, AProjectile p) {
        EventBus.get().notifyEventListenerObserver(new DTOEvent(EEventType.REMOVE_ELEMENT, new UIDTO( root, p.getImgView())));
    }
    private void applyDamage(ACharacterPlayable target, double amount) {
        double newHealth = target.getHealth() - (target.getInitial_Health() * amount);
        target.setHealth(newHealth);
        EventBus.get().notifyEventListenerObserver(new DTOEvent(EEventType.DAMAGED, new DamageData(target, amount)));
    }
    private void applyCollision(
            ACharacterPlayable target,
            double dx, double dy,
            double rebound) {

        ImageView img = target.getImgView();
        Rectangle shape = target.getCld().getShape();
        ProgressBar bar = target.getProgressBar();
        VBox vbox = target.getvBox();

        Platform.runLater(() -> {
            img.setX(img.getX() + dx * rebound);
            img.setY(img.getY() + dy * rebound);

            shape.setLayoutX(shape.getLayoutX() - dx * rebound);
            shape.setLayoutY(shape.getLayoutY() - dy * rebound);

            bar.setTranslateX(bar.getTranslateX() + dx * rebound * 0.4);
            bar.setTranslateY(bar.getTranslateY() + dy * rebound * 0.4);

            vbox.setTranslateX(vbox.getTranslateX() + dx * rebound * 0.4);
            vbox.setTranslateY(vbox.getTranslateY() + dy * rebound * 0.4);
        });
    }



    @Override
    public void normalAttack(double dt, ACharacterEnemy subject, ACharacterPlayable target) {

        AFireWeapon fireWeapon = (AFireWeapon) subject.getWeapon();
        initAttack(dt, subject, target);

        ProgressBar subjectBar = subject.getProgressBar();
        ProgressBar targetBar = target.getProgressBar();


        moveEnemyIfAlive(dt, subject, target);

        if (subjectBar.getProgress() > 0.1 &&
                !subject.attack_flag &&
                targetBar.getProgress() > 0.1) {

            updateProjectile(dt, subject, target, fireWeapon);

            AProjectile p = fireWeapon.getProjectile();
            Collider projectileCld = p.getCld();


            if (p.isArrived(target.getX(), target.getY())) {
                removeProjectile(target.root, p);
                subject.attack_flag = true;
                return;
            }


            if (projectileCld.getShape().intersects(target.getCld().getShape().getBoundsInLocal())) {

                Collider cld = target.getCld();

                double speed = target.getSpeed();
                double rebound = subject.getREBOUND();

                if (cld.canHit(Direction.RIGHT))
                    applyCollision(target, -speed, 0, rebound);

                if (cld.canHit(Direction.LEFT))
                    applyCollision(target,  speed, 0, rebound);

                if (cld.canHit(Direction.DOWN))
                    applyCollision(target, 0,  speed, rebound);

                if (cld.canHit(Direction.UP))
                    applyCollision(target, 0, -speed, rebound);

                removeProjectile(target.root, p);
                applyDamage(target, 0.2);

                subject.attack_flag = true;
            }
        }

        if (subjectBar.getProgress() < 0.1 &&
                !fireWeapon.getProjectile().isArrived(target.getX(), target.getY())) {
            removeProjectile(target.root, fireWeapon.getProjectile());
        }
    }

    @Override
    public void initAttack(double deltatime, ACharacterEnemy enemy, ACharacterPlayable player) {
        if (enemy.attack_flag && player.getProgressBar().getProgress() > 0.1 && enemy.getProgressBar().getProgress() > 0.1) {

            enemy.attack_flag = false;


            AProjectile p = new NormalAProjectile(EGameImages.ProvaAttaccoEnemy.getImage(), enemy.getX(), enemy.getY(), player.getX(), player.getY());
            Platform.runLater(() -> {
                player.root.getChildren().add(p.getImgView());
            });
            ((AFireWeapon) enemy.getWeapon()).getMag().set(0, p);

        }


    }
}
