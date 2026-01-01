package com.example.game;

public class DTOUIEvent {
    public final EUIEventType type;
    public final Object data;
    public DTOUIEvent(EUIEventType type, Object data)
    {
        this.type = type;
        this.data = data;
    }
}
