package com.example.game.Scene;

import com.example.game.Application.Command;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverScene {

    public static Scene create(Command retry_command, Command exit_command) {

        Text title = new Text("GAME OVER");
        title.setFont(Font.font(48));
        title.setFill(Color.RED);

        Button retry = new Button("Riprova");
        Button exit = new Button("Esci");

        retry.setOnAction(e -> retry_command.execute());
        exit.setOnAction(e -> exit_command.execute());

        VBox root = new VBox(20, title, retry, exit);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;");

        return new Scene(root, 600, 400);
    }
}
