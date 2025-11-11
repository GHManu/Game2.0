package com.example.game;

public class PlayerFactory implements ICharacterPlayableFactory{

    @Override
    public ACharacterPlayable createPlayer(String playerType) {

        switch (playerType.toLowerCase().trim()){
            case "type1":
                return new PlayerType1();
            default:
                return null;
        }
    }
}
