package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    boolean attack_flag;
    final double REBOUND = 2.56784;
    protected boolean goingDown;




    public ACharacterEnemy(){

    }
    public ACharacterEnemy(IFightStrategy fightStrategy){
        setFightStrategy(fightStrategy);
    }


}
