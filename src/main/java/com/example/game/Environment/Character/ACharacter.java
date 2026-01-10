package com.example.game.Environment.Character;

import com.example.game.Environment.AEntity;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.IMovementStrategy;
import com.example.game.UI.EGameImages;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.IFightStrategy;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

//17 righe

public abstract class ACharacter extends AEntity {

    private ProgressBar progressBar;
    private VBox vBox;
    private double initial_Health = 2450.431;
    private double health;
    private double strength;
    private double speed;

    private Direction direction = Direction.DOWN;


    protected boolean init_attack_flag;
    protected boolean attack_flag;

    private IMovementStrategy movementStrategy;
    private IFightStrategy fightStrategy;
    private AWeapon weapon;


    public AWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AWeapon weapon) {

        this.weapon = weapon;
    }

    public IMovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public final void changeImage(Image image){
        for(EGameImages ea : EGameImages.values()){
            if(ea.getImage() == image){
                Platform.runLater(() -> {
                    getImgView().setImage(image);
                });
                break;
            }
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public double getInitial_Health() {
        return initial_Health;
    }

    public void setInitial_Health(double initial_Health) {
        this.initial_Health = initial_Health;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public boolean isAttack_flag() {
        return attack_flag;
    }

    public void setAttack_flag(boolean attack_flag) {
        this.attack_flag = attack_flag;
    }

    public boolean isInit_attack_flag() {
        return init_attack_flag;
    }

    public void setInit_attack_flag(boolean init_attack_flag) {
        this.init_attack_flag = init_attack_flag;
    }
    public IFightStrategy getFightStrategy() {
        return fightStrategy;
    }

    public void setFightStrategy(IFightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }
    public boolean isAlive(){ return this.getProgressBar().getProgress() > 0.1 && this.getHealth() > 0;}

    public void takeDamage(double amount){
        double newHealth = this.getHealth() - (this.getInitial_Health() * amount);
        this.setHealth(newHealth);

    }
}
