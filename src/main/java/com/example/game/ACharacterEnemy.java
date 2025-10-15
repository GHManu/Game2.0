package com.example.game;

public abstract class ACharacterEnemy extends ACharacter{
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
