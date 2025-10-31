package com.example.game;

import javafx.scene.image.Image;

public abstract class ACharacterEnemy extends ACharacter{

    boolean attack_flag;
    final double REBOUND = 2.56784;
    protected boolean goingDown;


    private IFightStrategy fightStrategy;
    private AWeapon weapon;

    public AWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AWeapon weapon) {
        this.weapon = weapon;
    }

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
