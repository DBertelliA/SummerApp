package com.example.summerapp.Interface.Implement;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.DataTypesSQL.SqlDataTypes;
import com.example.summerapp.DataTypesSQL.SqlSentencesType;
import com.example.summerapp.Interface.TablesAutoCreateAndTheirFuntionsInterface;
import org.jspecify.annotations.NonNull;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TablesCreaterFuntionsDaoImpl implements TablesAutoCreateAndTheirFuntionsInterface {
    static Connection conect = ConnectionMySQL.getInstance();
    static String sql;

    //necesito crear un sistema en el que se creen tablas mediante la introduccion de valores
    //Intuyo que debo de usar la combinacion de variables introducidos por consola u meterlos en la sentencia

    @Override
    public void addTable(String titleTable, String dataName, int valuesNumber) {
        StringBuilder sb = getSentenceForSql(titleTable, dataName, valuesNumber);

        sql = sb.toString();

        try (Statement pST = conect.createStatement()){
            pST.executeUpdate(sql);
            System.out.println("Sentencia de agregar tablas ejecutada: " + sb.toString());
        }catch (SQLException e){
            System.err.println(sb.toString());
            System.err.println(e);
        }
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
    //necesito remodelar el foreign key, que necesita un valor de referencia de otra tabla
    public String sentenceSelectSql (int i){
        switch (i){
            case 1 -> {
                return String.valueOf(SqlSentencesType.NOT_NULL).replace("_"," ");
            }
            case 2 -> {
                return String.valueOf(SqlSentencesType.PRIMARY_KEY).replace("_"," ");
            }
            case 3 -> {
                return String.valueOf(SqlSentencesType.FOREIGN_KEY + "REFERENCES ...").replace("_"," ");
            }
        }

        return " ";
    }
    private @NonNull StringBuilder getSentenceForSql(String titleTable, String dataName, int valuesNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + titleTable).append(" (");

        for (int j = 0; j < valuesNumber; j++) {
            if (j >= 1){
                dataName = JOptionPane.showInputDialog("Introduce el nombre de la data");
            }
            try {
                sb.append(dataName).append(" ").append(dataSelect(Integer.parseInt(JOptionPane.showInputDialog("Del 1 al 6 pal dato"))));
            }catch (NumberFormatException e){
                sb.append("BOOLEAN ");
            }
            try {
                sb.append(" ").append(sentenceSelectSql(Integer.parseInt(JOptionPane.showInputDialog("del 1 al 2 para el tipo, el 3 no"))));
            }catch (NumberFormatException e) {
                sb.append(" ");
            }
            if(j == valuesNumber -1){
                sb.append(" ");
            }else {
                sb.append(",\n");
            }
        }
        sb.append(");");
        return sb;
    }

    public static void main(String[] args) {
        TablesAutoCreateAndTheirFuntionsInterface tb = new TablesCreaterFuntionsDaoImpl();

        //----Agregacion de tablas----//
        //tb.addTable("tabla3", "data1", 1);



    }
}
