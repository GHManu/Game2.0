package com.example.game.UI;

//7 righe

public interface IScreenSettings {
    int initialSizeTile = 16;
    int scale = 3;

    int maxScreenWidth = 16;
    int maxScreenHeight = 12;

    int sizeTile = initialSizeTile*scale;


    int screenWidth = sizeTile * maxScreenWidth;
    int screenHeight = sizeTile * maxScreenHeight;
}
