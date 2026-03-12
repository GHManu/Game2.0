package com.example.game.Environment.Objects.Interactable.Weapon.Ranged;

import javafx.scene.image.Image;

public interface IProjectileFactory {
    AProjectile create( double from_x, double from_y, double to_x, double to_y, Image img);
}
