package com.example.game.Environment.Character.Playable;

import com.example.game.Environment.Character.Attack.CommonAttackFireWeaponPlayer;
import com.example.game.Environment.Character.Movement.NoInput.OneWayMovement;
import com.example.game.Environment.Character.Movement.WithInput.SixWaySmoothlyMovement;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.AWeaponFactory;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.AFireWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.FireWeaponFactory;
import com.example.game.InputManager.InputManager;

public class PlayerFactory extends ACharacterPlayableFactory{

    @Override
    public ACharacterPlayable createPlayer(String weaponType, String concreteWeapon, String movementType, String concreteMovement, InputManager input_manager) {
        ACharacterPlayable player = new Player();
        AWeaponFactory weapon_factory;

        switch (weaponType.replaceAll("\\s+", "").toLowerCase()){
            case "fireweapon":
                weapon_factory = new FireWeaponFactory();
                AWeapon weapon_selected = weapon_factory.createWeapon(concreteWeapon);
                player.setWeapon(weapon_selected);
                player.setFightStrategyPlayer(new CommonAttackFireWeaponPlayer((AFireWeapon) player.getWeapon()));
                break;

            default:
                break;
        }
        switch (movementType.replaceAll("\\s+", "").toLowerCase()){
            case "withoutinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "oneway":
                        player.setMovementStrategy(new OneWayMovement());
                        break;
                }
                break;
            case "withinput":
                switch(concreteMovement.replaceAll("\\s+", "").toLowerCase()){
                    case "sixway":
                        player.setMovementStrategy(new SixWaySmoothlyMovement(input_manager));
                        break;
                }


            default:
                break;
        }

        return player;
    }
}
