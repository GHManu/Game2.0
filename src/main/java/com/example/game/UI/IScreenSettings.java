package com.example.game.UI;

//7 righe

public interface IScreenSettings {
    int initial_size_tile = 16;
    int scale = 3;

    int max_screen_width = 16;
    int max_screen_height = 12;

    int size_tile = initial_size_tile *scale;


    int screenWidth = size_tile * max_screen_width;
    int screenHeight = size_tile * max_screen_height;
}
