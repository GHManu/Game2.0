package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

//17 righe

public abstract class ACharacter extends AEntity{
    //caratteristiche
    private ProgressBar progressBar;
    private VBox vBox;
    private double initial_Health = 2450.431;
    private double health;
    private double strength;
    private double speed;

    //direzioni: 8 totali
    private boolean dir_forward;
    private boolean dir_backward;
    private boolean dir_rightward;
    private boolean dir_leftward;
    private boolean dir_forward_oblq_right;
    private boolean dir_forward_oblq_left;
    private boolean dir_backward_oblq_right;
    private boolean dir_backward_oblq_left;
    private boolean goingDown; //per adesso per l'enemy

    private IMovementStrategyWithoutInput movementStrategyWithoutInput;
    private IMovementStrategyWithInput movementStrategyWithInput;




    private AWeapon weapon;

    public AWeapon getWeapon() {

        return weapon;
    }

    public void setWeapon(AWeapon weapon) {

        this.weapon = weapon;
    }


    public IMovementStrategyWithoutInput getMovementStrategyWithoutInput() {
        return movementStrategyWithoutInput;
    }

    public void setMovementStrategyWithoutInput(IMovementStrategyWithoutInput movementStrategyWithoutInput) {
        this.movementStrategyWithoutInput = movementStrategyWithoutInput;
    }

    public IMovementStrategyWithInput getMovementStrategyWithInput() {
        return movementStrategyWithInput;
    }

    public void setMovementStrategyWithInput(IMovementStrategyWithInput movementStrategyWithInput) {
        this.movementStrategyWithInput = movementStrategyWithInput;
    }

    protected final void changeImage(Image image){
        for(EGameImages ea : EGameImages.values()){
            if(ea.getImage() == image){
                Platform.runLater(() -> {
                    getImgView().setImage(image);
                });
                break;
            }
        }
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

    public boolean isDir_forward() {
        return dir_forward;
    }

    public void setDir_forward(boolean dir_forward) {
        this.dir_forward = dir_forward;
    }

    public boolean isDir_backward() {
        return dir_backward;
    }

    public void setDir_backward(boolean dir_backward) {
        this.dir_backward = dir_backward;
    }

    public boolean isDir_rightward() {
        return dir_rightward;
    }

    public void setDir_rightward(boolean dir_rightward) {
        this.dir_rightward = dir_rightward;
    }

    public boolean isDir_leftward() {
        return dir_leftward;
    }

    public void setDir_leftward(boolean dir_leftward) {
        this.dir_leftward = dir_leftward;
    }

    public boolean isDir_forward_oblq_right() {
        return dir_forward_oblq_right;
    }

    public void setDir_forward_oblq_right(boolean dir_forward_oblq_right) {
        this.dir_forward_oblq_right = dir_forward_oblq_right;
    }

    public boolean isDir_forward_oblq_left() {
        return dir_forward_oblq_left;
    }

    public void setDir_forward_oblq_left(boolean dir_forward_oblq_left) {
        this.dir_forward_oblq_left = dir_forward_oblq_left;
    }

    public boolean isDir_backward_oblq_right() {
        return dir_backward_oblq_right;
    }

    public void setDir_backward_oblq_right(boolean dir_backward_oblq_right) {
        this.dir_backward_oblq_right = dir_backward_oblq_right;
    }

    public boolean isDir_backward_oblq_left() {
        return dir_backward_oblq_left;
    }

    public void setDir_backward_oblq_left(boolean dir_backward_oblq_left) {
        this.dir_backward_oblq_left = dir_backward_oblq_left;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }
}
