package com.example.game;


public class DTO {
    private Object root;
    private Object object;

    public DTO(Object root, Object object) {
        this.root = root;
        this.object = object;
    }

    public Object getRoot() {
        return root;
    }

    public void setRoot(Object root) {
        this.root = root;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object to_remove) {
        this.object = to_remove;
    }
}
