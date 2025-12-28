package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//5 righe

public abstract class AEntity implements IObserver{

    private Image img;
    private ImageView imgView;


    private double x;
    private double y;


    private Collider cld;

    private AStatsObject statsObject;
    private ISubject subject;

    public AStatsObject getStatsObject() {
        return statsObject;
    }

    public void setStatsObject(AStatsObject statsObject) {
        this.statsObject = statsObject;
    }

    public ISubject getSubject() {
        return subject;
    }

    public void setSubject(ISubject subject) {
        this.subject = subject;
    }

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
