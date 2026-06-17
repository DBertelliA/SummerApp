package com.example.summerapp.Interface.Implement;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Interface.SqlDataTypes;
import com.example.summerapp.Interface.SqlSentencesType;
import com.example.summerapp.Interface.TablesAutoCreateAndTheirFuntionsInterface;

import javax.swing.*;
import java.sql.Connection;
import java.text.ParseException;

public class TablesCreaterFuntionsDaoImpl implements TablesAutoCreateAndTheirFuntionsInterface {
    static Connection conect = ConnectionMySQL.getInstance();
    static String sql;

    //necesito crear un sistema en el que se creen tablas mediante la introduccion de valores
    //Intuyo que debo de usar la combinacion de variables introducidos por consola u meterlos en la sentencia

    @Override
    public void addTable(String dataName, String titleTable, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + titleTable).append(" (");

        for (int j = 0; j < i; j++) {
            try {
                sb.append(dataName).append(" ").append(dataSelect(Integer.parseInt(JOptionPane.showInputDialog("Introduce lo que sabes desa"))));
                }catch (NumberFormatException e){
                    sb.append("BOOLEAN ");
                    }
            try {
                sb.append(" ").append(sentenceSelectSql(Integer.parseInt(JOptionPane.showInputDialog("Introduce lo que sabes desa"))));
                }catch (NumberFormatException e) {
                    sb.append(" ");
                    }
                if(j == i-1){
                    sb.append(" ");
                }else {
                    sb.append(",\n");
                }
        }
        sb.append(");");





    }

    @Override
    public void dataSearch() {

    }

    @Override
    public void showAllTables() {

    }

    @Override
    public void deleteTables() {

    }

    @Override
    public void promptExexuter() {

    }


    public String dataSelect (int i){
        switch (i){
            case 1 -> {
                return String.valueOf(SqlDataTypes.TEXT);
            }
            case 2 -> {
                return String.valueOf(SqlDataTypes.INTEGER);
            }
            case 3 -> {
                return String.valueOf(SqlDataTypes.DOUBLE);
            }
            case 4 -> {
                return String.valueOf(SqlDataTypes.BOOLEAN);
            }
            case 5 -> {
                return String.valueOf(SqlDataTypes.TIME);
            }
            case 6 -> {
                return String.valueOf(SqlDataTypes.DATE);
            }
        }
        return String.valueOf(SqlDataTypes.BOOLEAN);
    }
    public String sentenceSelectSql (int i){
        switch (i){
            case 1 -> {
                return String.valueOf(SqlSentencesType.NOT_NULL).replace("_"," ");
            }
            case 2 -> {
                return String.valueOf(SqlSentencesType.PRIMARY_KEY).replace("_"," ");
            }
            case 3 -> {
                return String.valueOf(SqlSentencesType.FOREIGN_KEY).replace("_"," ");
            }
        }

        return " ";
    }

}
