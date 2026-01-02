package com.example.game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VictoryScene {

    public static Scene create(Runnable onRetry, Runnable onExit) {

        Text title = new Text("YOU WIN!");
        title.setFont(Font.font(48));
        title.setFill(Color.GREEN);

        Button retry = new Button("Rigioca");
        Button exit = new Button("Esci");

        retry.setOnAction(e -> {
            EventBus.get().removeAll(); onRetry.run();});
        exit.setOnAction(e -> onExit.run());

        VBox root = new VBox(20, title, retry, exit);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;");

        return new Scene(root, 600, 400);
    }
}

