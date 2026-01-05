package com.example.game.UI;

import com.example.game.Environment.Character.Attack.DamageData;
import com.example.game.Environment.Character.ACharacter;
import com.example.game.Event.DTOEvent;
import com.example.game.Event.EEventType;
import com.example.game.Event.EventBus;
import com.example.game.Event.IEventListenerObserver;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;


public class HUD implements IEventListenerObserver {

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

    private HUD()
    {
        EventBus.get().addEventListenerObserver(this);
    }

    @Override
    public void update(DTOEvent dto_event) {

        switch(dto_event.getType()){
            case EEventType.DAMAGED:
                DamageData data = (DamageData) dto_event.getData();
                updateProgressBar(data.getCharacter(), data.getDamage());
                break;
            case EEventType.REMOVE_ELEMENT:
                UIDTO UIDTO1 = (UIDTO) dto_event.getData();
                removeElement( UIDTO1.getRoot(), UIDTO1.getNode());
                break;
            case EEventType.ADD_ELEMENT:
                UIDTO UIDTO2 = (UIDTO) dto_event.getData();
                addElement(UIDTO2.getRoot(), UIDTO2.getNode());
                break;
            default:
                break;
        }

    }

    private void updateProgressBar(ACharacter character, double damage)
    {
        Platform.runLater(() -> {
            double progress = (character.getProgressBar().getProgress() - damage);
            character.getProgressBar().setProgress(progress);
        });
    }

    private void removeElement(Group root, Node node){
        Platform.runLater(() -> root.getChildren().remove(node));
    }

    private void addElement(Group root, Node node){
        Platform.runLater(() -> root.getChildren().add(node));
    }
}
