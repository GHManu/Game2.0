package com.example.game;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class EightWaySmoothlyMovementWithoutInput implements IMovementStrategyWithInput {
    @Override
    public void movement(double deltatime,  ACharacter target, Set<KeyCode> keysPressed) {
        if ( (keysPressed.contains(AInputCommands.forward) || keysPressed.contains(AInputCommands.forwardArrow)) && target.y >0
        ) {
            target.dir_forward = true;

            target.changeImage(EGameImages.Back_Pg.getImage());
            if( target.cld.fr)   this.moveUp(deltatime, target);
        }
        else{
            target.dir_forward = false;
        }

        if ( (keysPressed.contains( AInputCommands.backward) || keysPressed.contains(AInputCommands.backwardArrow)) &&  target.y < (IScreenSettings.screenHeight- IScreenSettings.sizeTile)
        ) {
            target.dir_backward = true;

            target.changeImage(EGameImages.Front_Pg.getImage());
            if( target.cld.br)  this.moveDown(deltatime, target);
        }
        else{
            target.dir_backward = false;
        }

        if ( (keysPressed.contains(AInputCommands.leftward) || keysPressed.contains(AInputCommands.leftwardArrow)) &&  target.x > 0
        ) {
            target.dir_leftward = true;


            target.changeImage(EGameImages.Left_Side_Pg.getImage());
            if( target.cld.dx) this.moveLeft(deltatime, target);
        }
        else{
            target.dir_leftward = false;
        }

        if ( (keysPressed.contains(AInputCommands.rightward) || keysPressed.contains(AInputCommands.rightwardArrow)) && target.x < (IScreenSettings.screenWidth- IScreenSettings.sizeTile)
        ) {
            target.dir_rightward = true;

            target.changeImage(EGameImages.Right_Side_Pg.getImage());
            if( target.cld.sx)    this.moveRight(deltatime, target);
        }
        else{
            target.dir_rightward = false;
        }
    }


    //implementazione metodi movimento

    protected void moveUp(double deltaTime, ACharacter target) {
        target.y -= target.speed * deltaTime ;
        //playerView.setY(y);
        Platform.runLater(() -> { target.imgView.setLayoutY(target.y); target.cld.ret.setY(target.y);   target.vBox.setLayoutY(target.y-20);});
    }


    protected void moveDown(double deltaTime, ACharacter target) {
        target.y += target.speed * deltaTime ;
        // playerView.setY(y);
        Platform.runLater(() -> {
            target.imgView.setLayoutY(target.y); target.cld.ret.setY(target.y); target.vBox.setLayoutY(target.y-20);});
    }


    protected void moveLeft(double deltaTime, ACharacter target) {
        target.x -= target.speed * deltaTime ;
        //playerView.setX(x);
        Platform.runLater(() -> {
            target.imgView.setLayoutX(target.x); target.cld.ret.setX(target.x); target.vBox.setLayoutX(target.x);});
    }


    protected void moveRight(double deltaTime, ACharacter target) {
        target.x += target.speed * deltaTime;
        //playerView.setX(x);
        Platform.runLater(() -> {
            target.imgView.setLayoutX(target.x); target.cld.ret.setX(target.x); target.vBox.setLayoutX(target.x);});
    }

}
