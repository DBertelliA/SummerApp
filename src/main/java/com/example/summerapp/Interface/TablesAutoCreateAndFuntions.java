package com.example.summerapp.Interface;

public interface TablesAutoCreateAndFuntions { //Too loong
    public boolean addTable(String titleTable, String dataName, int valuesNumber);
    public boolean insertData(String titleTable);
    public void updateData(String tableName, String data, String dataChange);
    public void deleteData(String tableName, String data, String dataParam);
    public String dataSearch(String titleName, String dataName, String dataParam, String param);
    public boolean showAllTables();
    public boolean deleteTables(String tableName);
    public String promptExexuter(String prompt);
}
