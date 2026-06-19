package com.example.summerapp.Interface;

public interface TablesAutoCreateAndTheirFuntionsInterface { //Too loong
    public void addTable(String titleTable, String dataName, int valuesNumber);
    public void insertData(String titleTable);
    public void dataSearch(String titleName, String dataName, String dataParam, String param);
    public void showAllTables();
    public void deleteTables(String tableName);
    public void promptExexuter(String prompt);
}
