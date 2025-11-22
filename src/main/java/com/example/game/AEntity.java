package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//5 righe

public abstract class AEntity {

    //immagine
    private Image img;
    private ImageView imgView;

    //posizione
    private double x;
    private double y;

    //Collider
    private Collider cld;


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Collider getCld() {
        return cld;
    }

    public void setCld(Collider cld) {
        this.cld = cld;
    }
}
