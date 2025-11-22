package com.example.game;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class EightWaySmoothlyMovementWithoutInput implements IMovementStrategyWithInput {
    @Override
    public void movement(double deltatime,  ACharacter target, Set<KeyCode> keysPressed) {
        if ( (keysPressed.contains(AInputCommands.forward) || keysPressed.contains(AInputCommands.forwardArrow)) && target.getY() >0
        ) {
            target.setDir_backward(true);

            target.changeImage(EGameImages.Back_Pg.getImage());
            if( target.getCld().fr)   this.moveUp(deltatime, target);
        }
        else{
            target.setDir_forward(false);
        }

        if ( (keysPressed.contains( AInputCommands.backward) || keysPressed.contains(AInputCommands.backwardArrow)) &&  target.getY() < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)
        ) {
            target.setDir_forward(true);

            target.changeImage(EGameImages.Front_Pg.getImage());
            if( target.getCld().br)  this.moveDown(deltatime, target);
        }
        else{
            target.setDir_backward(false);
        }

        if ( (keysPressed.contains(AInputCommands.leftward) || keysPressed.contains(AInputCommands.leftwardArrow)) &&  target.getX() > 0
        ) {
            target.setDir_leftward(true);


            target.changeImage(EGameImages.Left_Side_Pg.getImage());
            if( target.getCld().dx) this.moveLeft(deltatime, target);
        }
        else{
            target.setDir_leftward(false);
        }

        if ( (keysPressed.contains(AInputCommands.rightward) || keysPressed.contains(AInputCommands.rightwardArrow)) && target.getX() < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)
        ) {
            target.setDir_rightward(true);

            target.changeImage(EGameImages.Right_Side_Pg.getImage());
            if( target.getCld().sx)    this.moveRight(deltatime, target);
        }
        else{
            target.setDir_rightward(false);
        }
    }


    //implementazione metodi movimento

    protected void moveUp(double deltaTime, ACharacter target) {
        double y = target.getY();
        y -= target.getSpeed() * deltaTime ;
        target.setY(y);
        //playerView.setY(y);
        Platform.runLater(() -> { target.getImgView().setLayoutY(target.getY()); target.getCld().ret.setY(target.getY());   target.getvBox().setLayoutY(target.getY()-20);});
    }


    protected void moveDown(double deltaTime, ACharacter target) {
        double y = target.getY();
        y += target.getSpeed() * deltaTime ;
        target.setY(y);

        // playerView.setY(y);
        Platform.runLater(() -> {
            target.getImgView().setLayoutY(target.getY()); target.getCld().ret.setY(target.getY()); target.getvBox().setLayoutY(target.getY()-20);});
    }


    protected void moveLeft(double deltaTime, ACharacter target) {
        double x = target.getX();
        x -= target.getSpeed() * deltaTime ;
        target.setX(x);
        //playerView.setX(x);
        Platform.runLater(() -> {
            target.getImgView().setLayoutX(target.getX()); target.getCld().ret.setX(target.getX()); target.getvBox().setLayoutX(target.getX());});
    }


    protected void moveRight(double deltaTime, ACharacter target) {
        double x = target.getX();
        x += target.getSpeed() * deltaTime ;
        target.setX(x);
        //playerView.setX(x);
        Platform.runLater(() -> {
            target.getImgView().setLayoutX(target.getX()); target.getCld().ret.setX(target.getX()); target.getvBox().setLayoutX(target.getX());});
    }

}
