package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import java.util.Map;
import java.util.function.Supplier;

import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.AWeaponFactory;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;

public class FireWeaponFactory extends AWeaponFactory {

    private static final Map<EConcreteWeapon, Supplier<AFireWeapon>> fire_weapon_registry = Map.of(
            EConcreteWeapon.NORMAL_PISTOL, NormalPistol::new
        );

    @Override
    public AWeapon createWeapon(EConcreteWeapon type) {
        return fire_weapon_registry.get(type).get();
    }
}
