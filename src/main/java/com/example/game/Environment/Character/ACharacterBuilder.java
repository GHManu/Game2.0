package com.example.game.Environment.Character;

import com.example.game.Environment.Character.Movement.EConcreteMovement;
import com.example.game.Environment.Character.Movement.EMovementType;
import com.example.game.Environment.Character.Movement.IMovementStrategy;
import com.example.game.Environment.Character.Movement.NoInput.OneWayMovement;
import com.example.game.Environment.Character.Movement.WithInput.SixWaySmoothlyMovement;
import com.example.game.Environment.Object.Interactable.Weapon.AWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EConcreteWeapon;
import com.example.game.Environment.Object.Interactable.Weapon.EWeaponType;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.EProjectileType;
import com.example.game.Environment.Object.Interactable.Weapon.Ranged.FireWeaponFactory;
import com.example.game.InputManager.InputManager;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ACharacterBuilder<T extends ACharacter> {

    protected EConcreteWeapon concreteWeapon;
    protected EProjectileType projectileType;
    protected EConcreteMovement concreteMovement;
    protected EMovementType movementType;
    protected EWeaponType weaponType;
    protected InputManager inputManager;


    protected static final Map<EConcreteWeapon, Supplier<AWeapon>> WEAPON_REGISTRY = Map.of(
            EConcreteWeapon.NORMAL_PISTOL, () -> new FireWeaponFactory().createWeapon(EConcreteWeapon.NORMAL_PISTOL)
    );

    protected static final Map<EConcreteMovement, Function<InputManager, IMovementStrategy>> MOVEMENT_REGISTRY = Map.of(
            EConcreteMovement.SIX_WAY, im -> new SixWaySmoothlyMovement(im),
            EConcreteMovement.ONE_WAY,  im -> new OneWayMovement()
    );


    public ACharacterBuilder<T> weaponType(EWeaponType w) { this.weaponType = w; return this; }
    public ACharacterBuilder<T> concreteWeapon(EConcreteWeapon w) { this.concreteWeapon = w; return this; }
    public ACharacterBuilder<T> projectileType(EProjectileType p) { this.projectileType = p; return this; }
    public ACharacterBuilder<T> movementType(EMovementType w) { this.movementType = w; return this; }
    public ACharacterBuilder<T> concreteMovement(EConcreteMovement m) { this.concreteMovement = m; return this; }
    public ACharacterBuilder<T> inputManager(InputManager im) { this.inputManager = im; return this; }


    protected void applyWeapon(ACharacter character) {
        if (concreteWeapon == null) return;
        AWeapon w = WEAPON_REGISTRY.get(concreteWeapon).get();
        character.setWeapon(w);
        if (projectileType == null) projectileType = EProjectileType.NORMAL;
    }

    protected void applyMovement(ACharacter character) {
        if (concreteMovement == null) return;
        character.setMovementStrategy(MOVEMENT_REGISTRY.get(concreteMovement).apply(inputManager));
    }


    protected abstract void applyFightStrategy(T character);
    public abstract T build();
}