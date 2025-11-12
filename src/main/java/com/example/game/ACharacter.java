package com.example.game;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

//17 righe

public abstract class ACharacter extends AEntity{
    //caratteristiche
    protected ProgressBar progressBar;
    protected VBox vBox;
    protected final double initial_Health = 2450.431;
    protected double health;
    protected double strength;
    protected double speed;

    //direzioni: 8 totali
    protected boolean dir_forward;
    protected boolean dir_backward;
    protected boolean dir_rightward;
    protected boolean dir_leftward;
    protected boolean dir_forward_oblq_right;
    protected boolean dir_forward_oblq_left;
    protected boolean dir_backward_oblq_right;
    protected boolean dir_backward_oblq_left;
    private IMovementStrategyWithoutInput movementStrategyWithoutInput;
    private IMovementStrategyWithInput movementStrategyWithInput;

    //per adesso per l'enemy
    protected boolean goingDown;


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
                    imgView.setImage(image);
                });
                break;
            }
        }
    }


}
