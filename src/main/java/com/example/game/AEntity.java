package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//5 righe

public abstract class AEntity {

    //immagine
    protected Image img;
    protected ImageView imgView;

    //posizione
    protected double x;
    protected double y;

    //Collider
    protected Collider cld;
}
