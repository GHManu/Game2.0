package com.example.game;

import javafx.application.Platform;
import javafx.scene.Group;


public class HUD implements IEventListenerUIObserver {

    public HUD()
    {
        EventUIBus.get().addEventListenerObserver(this);
    }

    @Override
    public void update(DTOUIEvent dto_event) {

        switch(dto_event.type){
            case EUIEventType.ENEMY_DAMAGED:
                DamageData data = (DamageData) dto_event.data;
                updateProgressBar(data.getCharacter(), data.getDamage());
                break;
            case EUIEventType.REMOVE_ELEMENT:
                DTO dto1 = (DTO) dto_event.data;
                removeElement((Group) dto1.getRoot(), dto1.getObject());
                break;
            case EUIEventType.ADD_ELEMENT:
                DTO dto2 = (DTO) dto_event.data;
                addElement((Group) dto2.getRoot(), dto2.getObject());
                break;
            default:
                break;
        }

    }

    private void updateProgressBar(ACharacter character, double damage)
    {
        Platform.runLater(() -> {
            double progress = character.getHealth() / character.getInitial_Health();
            character.getProgressBar().setProgress(progress);
        });
    }

    private void removeElement(Group root, Object object){
        Platform.runLater(() -> root.getChildren().remove(object));
    }

    private void addElement(Group root, Object object){
        Platform.runLater(() -> root.getChildren().remove(object));
    }
}
