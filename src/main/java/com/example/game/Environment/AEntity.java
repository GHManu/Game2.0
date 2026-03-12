package com.example.game.Environment;

import com.example.game.UI.EGameImages;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//5 righe

public abstract class AEntity{

    private Image img;
    private ImageView img_view;


    private double x;
    private double y;


    private Collider collider;


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageView getImg_view() {
        return img_view;
    }

    public void setImg_view(ImageView img_view) {
        this.img_view = img_view;
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

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }


    public final void changeImage(Image image){
        for(EGameImages ea : EGameImages.values()){
            if(ea.getImage() == image){
                Platform.runLater(() -> {
                    getImg_view().setImage(image);
                });
                break;
            }
        }
    }
}
