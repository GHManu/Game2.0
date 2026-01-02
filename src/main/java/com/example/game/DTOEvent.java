package com.example.game;

public class DTOEvent {
    private final EEventType type;
    private final Object data;
    public DTOEvent(EEventType type, Object data)
    {
        this.type = type;
        this.data = data;
    }

    public EEventType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
