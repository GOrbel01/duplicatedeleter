package com.gorbel.file;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class FileCellFactory implements Callback<ListView<FileResult>, ListCell<FileResult>> {
    private ObservableList<FileResult> items;

    public FileCellFactory(ObservableList list) {
        items = list;
    }

    @Override
    public ListCell<FileResult> call(ListView<FileResult> fileResultListView) {
        return new FileCell(items);
    }
}
