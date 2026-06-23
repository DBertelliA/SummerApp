package com.example.summerapp.Interface.Functions;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.DataTypesSQL.SqlDataTypes;
import com.example.summerapp.DataTypesSQL.SqlSentencesType;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jspecify.annotations.NonNull;

import javax.swing.*;
import java.sql.*;

public class TablesFunctions implements TablesAutoCreateAndFuntions {
    static Connection conect = ConnectionMySQL.getInstance();
    static String sql;

    //necesito crear un sistema en el que se creen tablas mediante la introduccion de valores
    //Intuyo que debo de usar la combinacion de variables introducidos por consola u meterlos en la sentencia

    @Override
    public boolean addTable(String titleTable, String dataName, int valuesNumber) {
        StringBuilder sb = getSentenceForSql(titleTable, dataName, valuesNumber);

        try (Statement pST = conect.createStatement()){
            pST.executeUpdate(sb.toString());
            System.out.println("Sentencia de agregar tablas ejecutada: " + sb.toString());
            return true;
        }catch (SQLException e){
            System.err.println(sb.toString());
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean insertData(String titleTable){

        StringBuilder sb = sbForInsertData(titleTable);

        try (Statement st = conect.createStatement()){
                st.executeUpdate(sb.toString());
                System.out.println("Introducido");
                return true;
        }catch (SQLException e){
                System.err.println(e);
                System.err.println(sb.toString());
        }
        return false;
    }

    //Tener en cuenta que se te puede cambiar toda la fila si no ponemos los limitadores, pensaré como introducirlo mediante el javafx sin necesidad de añadir nada a los metodos
    @Override
    public void updateData(String tableName, String data, String dataChange) {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ").append(tableName).append(" SET ").append(data).append(" = ").append(dataChange);

        try (PreparedStatement sp = conect.prepareStatement(sb.toString())){
            sp.executeUpdate();
            System.out.println("Ejecutado");
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sb.toString());
        }

    }

    //Tengo que tener en cuenta el tipo de dato..., tener en cuenta tambien que la linea sql se ejecuta pero puede no eliminar ningun dato
    @Override
    public void deleteData(String tableName, String data, String dataParam) {
        boolean moreData = false;

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ").append(tableName).append(" WHERE ").append(data).append(" = ").append(dataParam);

        if(isUserWants(moreData)){
            sb.append("AND ").append(JOptionPane.showInputDialog(null,"Añade el nombre del dato")).append(" = ").append(JOptionPane.showInputDialog(null, "dame el dato"));
        }else{
            sb.append(";");
        }

        try (PreparedStatement sp = conect.prepareStatement(sb.toString())){
            sp.executeUpdate();
            System.out.println("Ejecutado");
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sb.toString());
        }

    }

    @Override
    public String dataSearch(String tableName, String dataName,String dataParam, String param) {
        boolean userWants = false;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(dataName).append(" FROM ").append(tableName);


        userWants = isUserWants(userWants);
        if (userWants){
            sb.append(" WHERE ").append(dataParam).append(" = ").append(param);
            userWants = isUserWants(userWants);
        }
        if(!userWants){
            sb.append(";");
        }

        if(userWants) {
            sb.append(paramExtensions());
        }

        try (Statement st = conect.createStatement()) {
            int j = numberDataLines(tableName);
            ResultSet rst = st.executeQuery(sb.toString());
            while (rst.next()) {
                for (int i = 1; i <= j; i++) {
                    System.out.println(rst.getObject(i));
                }
            }
            return sb.toString();
        }
        catch (SQLException e){
            if(e.getMessage().contains("Column Index out of range")){
                System.err.println("No se si es normal el out of range, pero no deberia ser un problema");
            }else {
                System.err.println(e);
                System.err.println(sb.toString());
            }
        }
        return "Something bad happened";

    }

    @Override
    public boolean showAllTables() {
        try (Statement st = conect.createStatement()){
            ResultSet rst = st.executeQuery("SHOW TABLES;");
            System.out.println("========================================");
            while (rst.next()){
                int j = numberDataLines(rst.getString(1));
                System.out.println("Numero de columnas de " +  rst.getString(1) + " es: " + j);
                try (Statement st2 = conect.createStatement()){
                    ResultSet rst2 = st2.executeQuery("SELECT * FROM " + (rst.getString(1)));
                    while(rst2.next()) {
                        for (int i = 1; i <= j; i++) {
                            System.out.println(rst2.getObject(i));
                        }
                    }
                }
                System.out.println("========================================");
            }
            return true;
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sql);
            return false;
        }

    }

    @Override
    public boolean deleteTables(String tableName) {
        sql = "DROP TABLE " + tableName + ";";
        try (Statement st = conect.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Eliminado");
            return true;
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sql);
        }
            return false;
    }

    //Crear una version muy limitada para luego expandirla de acuerdo a los datos que se dispongan
    @Override
    public String promptExexuter(String prompt) {
        String[] pata = prompt.split("FROM");

        try (Statement spT = conect.createStatement()){
            ResultSet rst = spT.executeQuery(prompt);
            int j = numberDataLines(pata[1]);
            if(prompt.contains("SELECT")) {
                while (rst.next()) {
                    for (int i = 1; i <= j; i++) {
                        System.out.println(rst.getObject(i));
                    }
                }
            }else {
                spT.executeUpdate(prompt);
            }
            System.out.println("Se ha ejecutado el comando");
            return prompt;
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(prompt);
        }
        return "Something bag happened";
    }



    private static boolean isUserWants(boolean userWants) {
        int a = JOptionPane.showConfirmDialog(
                null,
                "¿Quieres continuar añadiendo?",
                "Mas o no",
                JOptionPane.YES_NO_OPTION
        );
        if(a == 0){
            userWants = true;
        } else if (a == 1 || a == -1) {
            userWants = false;
        }
        return userWants;
    }

    //Este metodo está destinado a tener mas parametros, pero por ahora esto solo es una prueba
    public String paramExtensions(){
        StringBuilder sb = new StringBuilder();
        //while (userWants) {
        sb.append(" AND ").append("data1").append(" = ").append("\"T\"");
        //if (!userWants) {
        sb.append(";");
        //}
        //}
        return sb.toString();
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

    private static @NonNull StringBuilder sbForInsertData(String titleTable) {
        int j = numberDataLines(titleTable);
        String dataName;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " + titleTable).append(" VALUES (");

        try (Statement st = conect.createStatement()){
            sql = "DESCRIBE " + titleTable +" ;";
            ResultSet rst = st.executeQuery(sql);
            int i = 0;
            while (rst.next()) {
                if(rst.getString(2).equalsIgnoreCase("text") || rst.getString(2).contains("varchar")) {
                    sb.append("\"");
                    dataName = JOptionPane.showInputDialog("Introduce el contenido de la data que debe de ser (Comillas no importan): " + rst.getString(2));
                    sb.append(dataName);
                    sb.append("\"");
                }else {
                    dataName = JOptionPane.showInputDialog("Introduce el contenido de la data que debe de ser: " + rst.getString(2));
                    sb.append(dataName);
                }

                i++;
                if (i > j -1){
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
        return sb;
    }
    public static int numberDataLines(String tableName){
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
    //Cambio en el metodo muy fuerte...
    //haber que entienda que me he pasado un par de horitas jodiendo con los atributos
    //Le pasamos tanto el nombre de la tabla como el atributo que queremos transformar
    public static void contentTypeGiver(String titleTable,TableView<ObservableList<String>> tableView){

        try (Statement st = conect.createStatement()){
            sql = "DESCRIBE " + titleTable +" ;";
            ResultSet rst = st.executeQuery(sql);

            while (rst.next()) {
                //Primero, creamos una columna, que pille solo el nombre del dato
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rst.getString(1));

                //Luego, se compruena el tamaño de la tableview en general
                //Que para hacerse uno una idea, va aumentando a medida que el while pasa
                //puesto que cada vez que se invoca al TableColum, se está pegando otra columna
                int indice = tableView.getColumns().size();


                column.setCellValueFactory(data ->
                        new SimpleStringProperty(
                                data.getValue().get(indice)
                        )
                );
                tableView.getColumns().add(column);
            }

        }catch (SQLException e){
            System.err.println(e);
        }
    }


    public static void main(String[] args) {
        TablesAutoCreateAndFuntions tb = new TablesFunctions();
        //tb.insertData("datacatcheruser");

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

        //--Insertar data--
        // tb.insertData("tabla4");

        //Eliminar

        //tb.deleteTables("data1");

        //tb.showAllTables();

        //tb.promptExexuter("SELECT * FROM tabla3");

        //tb.dataSearch("tabla4","data2","data2", "1");
        //tb.deleteData("tabla4", "data1", "\"Testing2\"");

        //tb.updateData("tabla4","data1","\"t\"");

        //System.out.println(contentTypeGiver("tabla4"));
    }
}
