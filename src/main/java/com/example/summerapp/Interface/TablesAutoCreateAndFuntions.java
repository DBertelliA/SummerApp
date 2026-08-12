package com.example.summerapp.Interface;

import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

import java.util.List;

public interface TablesAutoCreateAndFuntions { //Too loong
    public boolean addTable(List<String> dataName, List<String> tipeForEach, String tableName, TextFlow dialogText, ImageView assistent, Spinner<Integer> numberValues);
    public boolean insertData(String titleTable, List<String> lStr, TextFlow dialogText, ImageView assistent);
    public void updateData(String tableName, List<String> stringChanger, List<String> stringBefore, TextFlow dialogText, ImageView assistent);
    public void deleteData(String tableName, List<String> valuesToDelete, List<String> valuesName, TextFlow dialogText, ImageView assistent);
    public String dataSearch(String titleName, List<String> valuesForSearch, TableView<ObservableList<String>> tableShower, TextFlow dialogText, ImageView assistent);
    public boolean deleteTables(String tableName, TextFlow dialogText, ImageView assistent);
    public boolean showAllTables();
    public String promptExexuter(String prompt);
}
