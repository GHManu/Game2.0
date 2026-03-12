package com.example.game.Scene;

import com.example.game.UI.IScreenSettings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public class GameScene extends Scene implements IScreenSettings {



    public GameScene(Parent parent, double width, double height, Paint color_background) {
        super(parent,width, height, color_background);

    }


}
