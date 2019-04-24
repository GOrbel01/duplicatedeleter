package com.gorbel.controller;

import com.gorbel.file.FileCellFactory;
import com.gorbel.file.FileResult;
import com.gorbel.scene.MScene;
import com.gorbel.scene.scenes.Scenes;
import com.gorbel.scene.singleton.StageManager;
import com.gorbel.window.WDimenesion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField fileTextField;

    @FXML
    private RadioButton fileSearchRadioButtonExt;

    @FXML
    private RadioButton fileSearchRadioButtonName;

    @FXML
    private Label searchLabel;

    @FXML
    private ListView<FileResult> filesListView;

    @FXML
    private ScrollPane itemsScrollPane;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private AnchorPane mainAnchorPane;

    private File dir;

    private StageManager stageManager = StageManager.getInstance();

    private ToggleGroup toggleGroup = new ToggleGroup();

    private ObservableList<FileResult> items = FXCollections.observableArrayList();

    private WDimenesion lastDimension = new WDimenesion();

    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileSearchRadioButtonName.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    searchLabel.setText("Search Name");
                }
            }
        });

        fileSearchRadioButtonExt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    searchLabel.setText("Search Type");
                }
            }
        });

        stageManager.getStage().widthProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("V: " + oldVal.doubleValue());
        });

        stageManager.getStage().widthProperty().addListener((obs, oldVal, newVal) -> {

        });

        stageManager.getStage().maximizedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    double mWidth = primaryScreenBounds.getWidth();
                    double mHeight = primaryScreenBounds.getHeight();
                    System.out.println("W: " + mWidth);
                    mainGridPane.setMinWidth(mWidth);
                    mainAnchorPane.setMinWidth(mWidth);
                    itemsScrollPane.setMinWidth(mWidth);
                    filesListView.setMinWidth(mWidth);
                } else {
                    mainGridPane.setMinWidth(lastDimension.getX());
                    mainAnchorPane.setMinWidth(lastDimension.getX());
                    itemsScrollPane.setMinWidth(lastDimension.getX());
                    filesListView.setMinWidth(lastDimension.getX());
                }
            }
        });

        this.fileSearchRadioButtonExt.setToggleGroup(toggleGroup);
        if (!toggleGroup.hasProperties()) {
            fileSearchRadioButtonExt.setSelected(true);
        }

        lastDimension.setX(primaryScreenBounds.getWidth());
        lastDimension.setY(primaryScreenBounds.getHeight());

        this.fileSearchRadioButtonName.setToggleGroup(toggleGroup);
        filesListView.setItems(items);
        filesListView.setCellFactory(new FileCellFactory(items));
    }

    public void onClickDirectoryButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        dir = directoryChooser.showDialog(stageManager.getStage());
        if (dir != null) {
            System.out.println(dir.getAbsolutePath());
            fileTextField.setText(dir.getAbsolutePath());
        }
    }


    public List<FileResult> toFileResultList(List<File> files) {
        List<FileResult> results = new ArrayList<>();
        for (File f : files) {
            results.add(new FileResult(f));
        }
        return results;
    }

    public void doSearch() {
        List<File> res = new ArrayList<>();
        searchFiles(dir.listFiles(), res);
        List<FileResult> cResults = toFileResultList(res);
        items.setAll(cResults);
    }

    private void searchFiles(File[] files, List<File> res) {
        if (files == null || files.length == 0) {
            return;
        } else {
            for (File f : files ) {
                if (f != null) {
                    if (!f.isDirectory()) {
                        res.add(f);
                    }
                    File[] newFiles = f.listFiles();
                    if (newFiles != null) searchFiles(newFiles, res);
                }
            }
        }
    }

    public void doDeleteSelected() {
        stageManager.setScene(Scenes.SECOND.getFileName(), Scenes.path);
        System.out.println("IC: " + MScene.instanceCount);
    }

    public void doDeleteFiles() {
        Controller c = (Controller) stageManager.getCurrentScene().getController();
        System.out.println(c.dir.getAbsolutePath());
    }
}
