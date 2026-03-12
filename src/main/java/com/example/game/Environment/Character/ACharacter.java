package com.example.game.Environment.Character;

import com.example.game.Environment.AEntity;
import com.example.game.Environment.Character.Movement.Direction;
import com.example.game.Environment.Character.Movement.IMovementStrategy;
import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Collider;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.util.List;


public abstract class ACharacter extends AEntity {

    private ProgressBar progress_bar;
    private VBox vbox;
    private double initial_Health = 2450.431;
    private double health;
    private double strength;
    private double speed;

    private Direction direction = Direction.DOWN;


    protected boolean init_attack_flag;
    protected boolean attack_flag;

    private IMovementStrategy movement_strategy;
    private AWeapon weapon;


    public void takeDamage(double amount){
        double newHealth = this.getHealth() - (this.getInitial_Health() * amount);
        this.setHealth(newHealth);

    }
    public boolean canMoveTo(double nextX, double nextY,
                             List<Collider> walls,
                             List<ACharacter> others) {
        for (Collider w : walls)
        {
            if (this.getCollider().intersectsAt(nextX, nextY, w)) { return false; }
        }

        for (AEntity other : others) {
            if (other != this &&
                    this.getCollider().intersectsAt(nextX, nextY, other.getCollider())) {
                return false;
            }
        }
        return true;
    }

    public AWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AWeapon weapon) {

        this.weapon = weapon;
    }

    public IMovementStrategy getMovement_strategy() {
        return movement_strategy;
    }

    public void setMovement_strategy(IMovementStrategy movement_strategy) {
        this.movement_strategy = movement_strategy;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public ProgressBar getProgress_bar() {
        return progress_bar;
    }

    public void setProgress_bar(ProgressBar progress_bar) {
        this.progress_bar = progress_bar;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
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

    public boolean isAlive(){ return this.getProgress_bar().getProgress() > 0.1 && this.getHealth() > 0;}



}
