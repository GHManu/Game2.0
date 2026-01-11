package com.example.game.Environment.Character.Movement;

import com.example.game.Environment.Character.ACharacter;
import com.example.game.InputManager.InputManager;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record DirectionSetting(
        Predicate<InputManager> input_check,
        Consumer<ACharacter> set_direction_flag,
        Consumer<ACharacter> set_image,
        BiFunction<Double, ACharacter, Boolean> move_if_allowed) {}
