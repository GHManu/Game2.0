package com.example.game;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
//2 righe
//implementiamo anche l'interfaccia Runnable, così da avere il metodo run()
public class GameScene extends Scene implements IScreenSettings {



    public GameScene(Parent parent, double width, double height, Paint colorBackground) {
        super(parent,width, height, colorBackground);

    }


}
