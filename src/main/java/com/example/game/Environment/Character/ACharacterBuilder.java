package com.example.game.Environment.Character;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Character.Movement.IMovementStrategy;
import com.example.game.Environment.Character.Movement.NoInput.OneWayMovement;
import com.example.game.Environment.Character.Movement.WithInput.SixWaySmoothlyMovement;
import com.example.game.Environment.Objects.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Objects.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.Environment.Objects.Interactable.Weapon.Ranged.FireWeaponFactory;
import com.example.game.InputManager.InputManager;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ACharacterBuilder<T extends ACharacter> {

    protected EConcreteWeapon concrete_weapon;
    protected EProjectileType projectile_type;
    protected EConcreteMovement concrete_movement;
    protected EMovementType movement_type;
    protected EWeaponType weapon_type;
    protected InputManager input_manager;


    protected static final Map<EConcreteWeapon, Supplier<AWeapon>> weapon_registry = Map.of(
            EConcreteWeapon.NORMAL_PISTOL, () -> new FireWeaponFactory().createWeapon(EConcreteWeapon.NORMAL_PISTOL)
    );

    protected static final Map<EConcreteMovement, Function<InputManager, IMovementStrategy>> movement_registry = Map.of(
            EConcreteMovement.SIX_WAY, im -> new SixWaySmoothlyMovement(im),
            EConcreteMovement.ONE_WAY,  im -> new OneWayMovement()
    );


    public ACharacterBuilder<T> weaponType(EWeaponType w) { this.weapon_type = w; return this; }
    public ACharacterBuilder<T> concreteWeapon(EConcreteWeapon w) { this.concrete_weapon = w; return this; }
    public ACharacterBuilder<T> projectileType(EProjectileType p) { this.projectile_type = p; return this; }
    public ACharacterBuilder<T> movementType(EMovementType w) { this.movement_type = w; return this; }
    public ACharacterBuilder<T> concreteMovement(EConcreteMovement m) { this.concrete_movement = m; return this; }
    public ACharacterBuilder<T> inputManager(InputManager im) { this.input_manager = im; return this; }


    protected void applyWeapon(ACharacter character) {
        if (concrete_weapon == null) return;
        AWeapon w = weapon_registry.get(concrete_weapon).get();
        character.setWeapon(w);
        if (projectile_type == null) projectile_type = EProjectileType.NORMAL;
    }

    protected void applyMovement(ACharacter character) {
        if (concrete_movement == null) return;
        character.setMovement_strategy(movement_registry.get(concrete_movement).apply(input_manager));
    }


    protected abstract void applyFightStrategy(T character);
    public abstract T build();
}