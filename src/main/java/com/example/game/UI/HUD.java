package com.example.game.UI;

import com.example.game.Environment.Character.ACharacter;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;


public class HUD {

    private volatile static HUD unique_instance;

    public static void  getInstance(){
        if(unique_instance == null){
            synchronized (HUD.class){
                if(unique_instance == null){
                    unique_instance = new HUD();
                }
            }
        }
    }


    public static void updateProgressBar(ACharacter character, double damage)
    {
        Platform.runLater(() -> {
            double progress = (character.getProgressBar().getProgress() - damage);
            character.getProgressBar().setProgress(progress);
        });
    }

    public static void removeElement(Group root, Node node){
        Platform.runLater(() -> root.getChildren().remove(node));
    }

    public static void addElement(Group root, Node node){
        Platform.runLater(() -> root.getChildren().add(node));
    }
}
