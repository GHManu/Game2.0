package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    Image attackImage;
    boolean attack_flag;
    NormalProjectile p;
    final double REBOUND = 2.56784;
    protected boolean goingDown;


    private IFightStrategy fightStrategy;
    public ACharacterEnemy(){

    }
    public ACharacterEnemy(IFightStrategy fightStrategy){
        setFightStrategy(fightStrategy);
    }

    public IFightStrategy getFightStrategy() {
        return fightStrategy;
    }

    public void setFightStrategy(IFightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }
}
