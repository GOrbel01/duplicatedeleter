package com.gorbel.scene.scenes;

public enum Scenes {
    MAIN("mainview"), SECOND("otherview");

    public static final String path = "/com/gorbel/";

    private String fileName;

    Scenes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
