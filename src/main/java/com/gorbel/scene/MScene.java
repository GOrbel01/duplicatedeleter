package com.gorbel.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class MScene extends Scene {
    public static int instanceCount = 0;
    private String sceneName;
    private Object controller;

    public MScene(Parent parent, String sceneName, Object controller) {
        super(parent);
        this.sceneName = sceneName;
        this.controller = controller;
        instanceCount++;
    }

    public String getSceneName() {
        return sceneName;
    }

    public Object getController() {
        return controller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MScene mScene = (MScene) o;
        return Objects.equals(sceneName, mScene.sceneName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneName);
    }
}
