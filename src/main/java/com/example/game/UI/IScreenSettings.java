package com.example.game.UI;

//7 righe

public interface IScreenSettings {
    //static e final sono ridondanti per le interfacce
    //cioè tutte le immagini (player, alberi, ecc...) saranno 16x16 pixel
    //dobbiamo scegliere una risoluzione giusta, perchè ad esempio il nostro 16x16 pixel con la 1920x1080 risulterà piccolo
    //quindi scegliamone una molto minore, ma ci servirà scalarla poichè il monitor avrà la risoluzione alta
    int initialSizeTile = 16;  //sarà la dimensione standard dei vari assets
    int scale = 3;    //16*3=48

    int maxScreenWidth = 16;
    int maxScreenHeight = 12;

    int sizeTile = initialSizeTile*scale;  //48x48, con questo ci faremo l'immagine del player e tutte le altre PERò
    //dell'imageView ovvero del contenitore
    // deve essere 48x48
    int screenWidth = sizeTile * maxScreenWidth;  //768 pixel
    int screenHeight = sizeTile * maxScreenHeight;    //576 pixel
}
