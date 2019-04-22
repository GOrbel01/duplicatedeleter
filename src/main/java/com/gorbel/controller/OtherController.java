package com.gorbel.controller;

import com.gorbel.scene.scenes.Scenes;
import com.gorbel.scene.singleton.StageManager;

public class OtherController {
    public void doTestAction() {
        StageManager.getInstance().setScene(Scenes.MAIN.getFileName(), Scenes.path);
    }
}
