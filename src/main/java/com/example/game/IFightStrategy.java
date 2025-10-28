package com.example.game;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public interface IFightStrategy {
    abstract void attack(double deltatime, ACharacterPlayable plr, ACharacterEnemy enemy);
}
