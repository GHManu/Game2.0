package com.example.game.Environment.Character.NPC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DialogueBox extends Pane {
    private final Text text;

    public DialogueBox() {
        setPrefSize(400, 120);
        setStyle( "-fx-background-color: rgba(0,0,0,0.6);" +
                "-fx-border-color: white;" + "-fx-border-width: 3px;" +
                "-fx-background-radius: 10;" + "-fx-border-radius: 10;" );
        text = new Text();
        text.setFill(Color.WHITE);
        text.setWrappingWidth(380);
        text.setLayoutX(10);
        text.setLayoutY(30);
        getChildren().add(text);
    }
    public void show(String line) { text.setText(line); }
}
