package com.example.summerapp.Interface;

import javafx.scene.control.TextField;

import java.util.List;

public interface TablesAutoCreateAndFuntions { //Too loong
    public boolean addTable(String titleTable, String dataName, int valuesNumber);
    public boolean insertData(String titleTable, List<String> lStr);
    public void updateData(String tableName, List<String> stringChanger, List<String> stringBefore);
    public void deleteData(String tableName, List<String> valuesToDelete, List<String> valuesName);
    public String dataSearch(String titleName, String dataName, String dataParam, String param);
    public boolean showAllTables();
    public boolean deleteTables(String tableName);
    public String promptExexuter(String prompt);
}
