package com.example.game;

public abstract class ACharacterEnemy extends ACharacter{
    private IFightBehaviour fight;
    public ACharacterEnemy(){

    }
    private void attack(double deltatime, Player plr){
        fight.attack(deltatime, plr);
    }

}
