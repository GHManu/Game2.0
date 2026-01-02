package com.example.game;


import javafx.scene.Group;
import javafx.scene.Node;

public class UIDTO {
    private Group root;
    private Node node;

    public UIDTO(Group root, Node node) {
        this.root = root;
        this.node = node;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
