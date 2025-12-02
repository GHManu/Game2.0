package com.example.game;

public class Sprint extends AMovementDecorator{


    public Sprint(ACharacter character) {
        this.setCharacter(character);
    }

    @Override
    void movement(double deltatime) {
        getCharacter().getMovementStrategy().movement(deltatime, this.getCharacter());
    }
}
