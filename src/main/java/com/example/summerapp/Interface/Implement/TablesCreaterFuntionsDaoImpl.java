package com.example.summerapp.Interface.Implement;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.DataTypesSQL.SqlDataTypes;
import com.example.summerapp.DataTypesSQL.SqlSentencesType;
import com.example.summerapp.Interface.TablesAutoCreateAndTheirFuntionsInterface;
import org.jspecify.annotations.NonNull;

import javax.swing.*;
import java.sql.*;

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
    public void insertData(String titleTable){
        int j = numberDataLines(titleTable);
        String dataName;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " + titleTable).append(" VALUES (");

            try (Statement st = conect.createStatement()){
                sql = "DESCRIBE " + titleTable +" ;";
                    ResultSet rst = st.executeQuery(sql);
                    int i = 0;
                        while (rst.next()) {
                            if(rst.getString(2).equalsIgnoreCase("text")) {
                                sb.append("\"");
                                dataName = JOptionPane.showInputDialog("Introduce el contenido de la data que debe de ser (Comillas no importan): " + rst.getString(2));
                                sb.append(dataName);
                                sb.append("\"");
                            }else {
                                dataName = JOptionPane.showInputDialog("Introduce el contenido de la data que debe de ser: " + rst.getString(2));
                                sb.append(dataName);
                            }

                            i++;
                            if (i > j-1){
                                System.out.println("Sigo");
                            }else{
                                sb.append(", ");
                            }
                        }
                System.out.println(i);
                        sb.append(" );");
                }catch (SQLException e){
                    System.err.println(e);
                }

            try (Statement st = conect.createStatement()){
                st.executeUpdate(sb.toString());
                System.out.println("Introducido");
            }catch (SQLException e){
                System.err.println(e);
                System.err.println(sb.toString());
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
    public int numberDataLines(String tableName){
        int dataNumber = 0;
        try (Statement st = conect.createStatement()){
            sql = "DESCRIBE " + tableName +" ;";
            ResultSet rst = st.executeQuery(sql);
                while (rst.next()) {
                    dataNumber++;
                }
            return dataNumber;
        }catch (SQLException e){
            System.err.println(e);
        }
        return 0;
    }
    public String contentTypeGiver(String titleTable){
        try (Statement st = conect.createStatement()){
            sql = "DESCRIBE " + titleTable +" ;";
            ResultSet rst = st.executeQuery(sql);
            while (rst.next()) {
                rst.getString(2);
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return " ";
    }

    public static void main(String[] args) {
        TablesAutoCreateAndTheirFuntionsInterface tb = new TablesCreaterFuntionsDaoImpl();

        //----Agregacion de tablas----//
        //tb.addTable("tabla4", "data1", 3);
//        int a = 0;
//        try (Statement st = conect.createStatement()){
//            sql = "DESCRIBE tabla4;";
//            ResultSet rst = st.executeQuery(sql);
//            //Primer elemento el nombre de la fila, segundo elemento, el tipo de dato
//            while (rst.next()) {
//                a++;
//                System.out.println(rst.getString(1) + " " + rst.getString(2) + " " + rst.getString(3) + " " + rst.getString(4) + " " + rst.getString(5));
//            }
//            System.out.println(a);
//        }catch (SQLException e){
//            System.err.println(e);
//        }

        tb.insertData("tabla4");


    }
}
