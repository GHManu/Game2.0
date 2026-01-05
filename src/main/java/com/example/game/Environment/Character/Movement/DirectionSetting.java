package com.example.game.Environment.Character.Movement;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.InputManager.InputManager;
import javafx.scene.input.KeyCode;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record DirectionSetting(
        Predicate<InputManager> input_check,
        Predicate<ACharacter> boundary_check,
        Consumer<ACharacter> set_direction_flag,
        Consumer<ACharacter> set_image,
        BiConsumer<Double, ACharacter> move_if_allowed) {}
