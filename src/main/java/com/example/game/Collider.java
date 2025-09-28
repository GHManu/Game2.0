package com.example.game;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

//circa 28 righe

public class Collider {
    Rectangle ret ;
    protected double x, y;
    boolean dx, sx, fr, br;


    public Collider(double x, double y, double width, double height){
        dx = sx = fr = br = true;
        this.x = x;
        this.y = y;
        ret = new Rectangle( this.x, this.y, width, height);    //posizione e grandezza
        ret.setArcHeight(10d);
        ret.setArcWidth(10d);
        //ret.setStroke(Color.RED);

    }
    protected void collision_Detected( Rectangle ret2, boolean block){
        //versione con problemi: poichè il pg si blocca
        //prima della collisione
//        double beforeColX = plr.cld.ret.getX();
//        double beforeColY = plr.cld.ret.getY();
//
//        if (plr.cld.ret.getBoundsInParent().intersects(enemy.cld.ret.getBoundsInParent())) {    //se interseco
//            if (beforeColX < (enemy.cld.ret.getX()) ){
//                sx = false;
//                System.out.println("Collisione da sinistra!");
//            } else if (beforeColX > enemy.cld.ret.getX()) {
//                dx = false;
//                System.out.println("Collisione da destra!");
//            }
//
//            if (beforeColY < enemy.cld.ret.getY()) {
//                fr = false;
//               System.out.println("Collisione dall'alto!");
//            } else if (beforeColY > enemy.cld.ret.getY()) {
//                br = false;
//                System.out.println("Collisione dal basso!");
//            }
//        }
//        else{
//            dx = sx = fr = br = true;
//        }

        if(  this.ret != null && ret2 != null) {

            if (this.ret.intersects(ret2.getBoundsInParent())) {
                //uso un gap per evitare che il pg si blocchi e quindi avvenga una sovrapposizione delle condizioni di collisione
                //restituisce la coordinata X dell'angolo superiore sinistro del rettangolo rispetto al parent.
                double deltaX = this.ret.getX() - ret2.getX();
                double deltaY = this.ret.getY() - ret2.getY();
                //esempi quando mi trovo a sinistra del nemico:
                //es. 436 - 484 = -48 che è la larghezza delle immagini
//            System.out.println(e1.cld.ret.getX()+"  "+ret2.cld.ret.getX());
//            System.out.println(e1.cld.ret.getY()+"  "+ret2.cld.ret.getY());
                //es. 214.049 - 188 = 26,049
                //quindi dato che il val ass di deltax è > di deltay mi si attiva la collisione da sinistra
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX < 0) {
                        // Collisione da sinistra: correggi posizione
                        this.sx = false;
                        //mi imposta la posizione x del colider del player alla distanza del collider del nemico - la larghezza che è 48
                        if(block)
                            Platform.runLater(() -> {
                                this.ret.setX(ret2.getX() - this.ret.getWidth());
                            });
                        //System.out.println("Collisione da sinistra!");
                    } else {
                        // Collisione da destra: correggi posizione
                        this.dx = false;
                        if(block) Platform.runLater(() -> {
                            this.ret.setX(ret2.getX() + ret2.getWidth());
                        });
                        //System.out.println("Collisione da destra!");
                    }
                } else {
                    if (deltaY < 0) {
                        // Collisione dall'alto: correggi posizione
                        this.br = false;
                        if(block) Platform.runLater(() -> {
                           this.ret.setY(ret2.getY() - this.ret.getHeight());
                        });
                       // System.out.println("Collisione dall'alto!");
                    } else {
                        // Collisione dal basso: correggi posizione
                        this.fr = false;
                        if(block) Platform.runLater(() -> {
                            this.ret.setY(ret2.getY() + ret2.getHeight());
                        });
                        //System.out.println("Collisione dal basso!");
                    }
                }
            } else this.dx = this.sx =this.fr = this.br = true;
        }
    }

}
