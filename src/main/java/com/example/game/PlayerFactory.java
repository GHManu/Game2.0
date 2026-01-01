package com.example.game;

public class PlayerFactory extends ACharacterPlayableFactory{

    @Override
    ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement) {
        ACharacterPlayable player = new Player();
        AWeaponFactory weapon_factory;

        switch (weaponType.replaceAll("\\s+", "").toLowerCase()){
            case "fireweapon":
                weapon_factory = new FireWeaponFactory();
                AWeapon weapon_selected = weapon_factory.createWeapon(concreteWeapon);
                player.setWeapon(weapon_selected);
                player.setFightStrategy(new AttackFireWeaponPlayer());
                break;

            default:
                break;
        }
        switch (movementType.replaceAll("\\s+", "").toLowerCase()){
            case "withoutinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "oneway":
                        player.setMovementStrategy(new OneWayMovementWithoutInput());
                        break;
                }
                break;
            case "withinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "sixway":
                        player.setMovementStrategy(new SixWaySmoothlyMovementWithInput());
                        break;
                }


            default:
                break;
        }

        return player;
    }
}
