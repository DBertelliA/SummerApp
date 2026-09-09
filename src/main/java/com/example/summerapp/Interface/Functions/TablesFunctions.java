package com.example.summerapp.Interface.Functions;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Controllers.MenuControllers.ErrorReactionsFramesController;
import com.example.summerapp.Controllers.MenuControllers.FrameController;
import com.example.summerapp.Controllers.MenuControllers.MenuController_All;
import com.example.summerapp.History.HelperStringHistory;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablesFunctions implements TablesAutoCreateAndFuntions {
    private static ErrorReactionsFramesController err;
    public TablesFunctions(ErrorReactionsFramesController err) {
        TablesFunctions.err = err;
    }

    static Connection conect = ConnectionMySQL.getInstance();
    static String sql;

    public static void dialogGenerator(ImageView assistent, int i,TextFlow dialogText, String message) {
        FrameController.framesView(assistent, i, false);
        Font font = Font.font(20);
        Text textAssign = new Text(message);

        textAssign.setFont(font);
        textAssign.setFill(Color.WHITE);

        textAssign.setTextAlignment(TextAlignment.LEFT);
        dialogText.getChildren().clear();
        dialogText.getChildren().add(textAssign);
    }

    @Override
    public boolean addTable(List<String> dataName, List<String> tipeForEach, String tableName, TextFlow dialogText, ImageView assistent, Spinner<Integer> numberValues, String user) {
        StringBuilder sb = new StringBuilder();

        //En tipeForEach: Lista unica con dos valores diferentes, impar, tipo de dato, par tipo de llave

        sb.append("CREATE TABLE ").append(tableName).append("( \n");
        int j = 0;
        for (int i = 0; i < dataName.size(); i++) {
            sb.append(dataName.get(i)).append(" ");
            for (int p = 0; p < 2; p++) {
                sb.append(tipeForEach.get(j)).append(" ");
                j++;
            }
            if (i < dataName.size() -1){
                sb.append(", \n");
            }
        }
        sb.append(");");

        try (Statement pST = conect.createStatement()){
            if (tableName.isEmpty()){throw new SQLException("bruh");}
            if (sb.toString().contains("null")){throw new SQLException("a");}
            pST.executeUpdate(sb.toString());
            System.out.println("Sentencia de agregar tablas ejecutada: " + sb.toString());

            dialogGenerator(assistent,1,dialogText, "Has agregado la tabla " + tableName);

            HelperStringHistory.historyMaker("El usuario" + user + " ha generado la tabla " + tableName);

            return true;
        }catch (SQLException e){
            boolean switcher = false;
            System.err.println(sb.toString());
            System.err.println(e);
            HelperStringHistory.historyMaker("Errrr/// El usuario " + user + " ha intentado agregar una nueva tabla con el nombre" + tableName + " ... No se ha podido");

            //err count es el numero de dialogos de cada error y debe coincidir con la cantidad de la lista con la cantidad impuesta -1 (el ultimo dialogo suelen ser puntos suspensivos)
            if(tableName.isEmpty()){
                System.out.println("Entró al no title");
                err.setNumberErr(4);
                err.setWhereErr(1);
                switcher = true;
            }

            if (e.getMessage().contains("already exists") && !switcher) {
                System.out.println("Entro al del nombre de la tabla");
                err.setNumberErr(1);
                err.setWhereErr(1);
                switcher = true;
            }

            if (!switcher) {
                for (int i = 0; i < dataName.size(); i++) {
                    if (dataName.get(i).isEmpty()) {
                        System.out.println("Entró al diferencial");
                        err.setNumberErr(2);
                        err.setWhereErr(1);
                        switcher = true;
                        break;
                    }
                }
            }
            if(!switcher) {
                System.out.println("Entró al del combox");
                err.setNumberErr(3);
                err.setWhereErr(1);
                switcher = true;
            }
        }
        return false;
    }

    @Override
    public boolean insertData(String titleTable, List<String> lStr, TextFlow dialogText, ImageView assistent,String user){
        StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO " + titleTable).append(" VALUES (");

            try (Statement st = conect.createStatement()){
                sql = "DESCRIBE " + titleTable +" ;";
                ResultSet rst = st.executeQuery(sql);
                int i = 0;
                while (rst.next()) {
                    if(rst.getString(2).equalsIgnoreCase("text") || rst.getString(2).contains("varchar")) {
                        sb.append("\"");
                        sb.append(lStr.get(i));
                        sb.append("\"");
                    }else {
                        sb.append(lStr.get(i));
                    }
                    i++;
                    if (i > lStr.size() -1){
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
                System.out.println(sb.toString());
                dialogGenerator(assistent,1,dialogText, "se ha insertado la data a la tabla " + titleTable);
                HelperStringHistory.historyMaker( "El usuario " + user + " ha insertado la datos a la tabla " + titleTable);
                return true;
        }catch (SQLException e){
                System.err.println(e);
                System.err.println(sb.toString());
            dialogGenerator(assistent,1,dialogText, "No se ha insertado la data a la tabla " + titleTable);
            HelperStringHistory.historyMaker("Errrr/// El usuario " + user + " no ha podido insertar los datos a la tabla " + titleTable);

            err.setWhereErr(2);
            err.setNumberErr(1);

        }
        return false;
    }

    //Tener en cuenta que se te puede cambiar toda la fila si no ponemos los limitadores, pensaré como introducirlo mediante el javafx sin necesidad de añadir nada a los metodos
    @Override
    public void updateData(String tableName, List<String> stringChanger, List<String> stringBefore, TextFlow dialogText, ImageView assistent,String user) {
        List<String> nameData = giverName(tableName, dialogText ,assistent);

        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ").append(tableName).append(" SET ");

        for (int i = 0; i < stringChanger.size(); i++) {
            sb.append(nameData.get(i)).append(" = ");
            try {
                sb.append(Double.parseDouble(stringChanger.get(i)));
            }catch (NumberFormatException e){
                sb.append("'").append(stringChanger.get(i)).append("'");
            }

            if (i < stringChanger.size() - 1){sb.append(", ");}
            else sb.append("\n");

        }
        sb.append("WHERE ");
        for (int i = 0; i < stringChanger.size(); i++) {
            sb.append(nameData.get(i)).append(" = ");
            try {
                sb.append(Double.parseDouble(stringBefore.get(i)));
            }catch (NumberFormatException e){
                sb.append("'").append(stringBefore.get(i)).append("'");
            }
            if (i < stringChanger.size() - 1){sb.append(" AND ");}
            else sb.append(";");
        }

        try (PreparedStatement sp = conect.prepareStatement(sb.toString())){
            sp.executeUpdate();
            dialogGenerator(assistent,1,dialogText, "Se han actualizado los datos seleccionados");
            HelperStringHistory.historyMaker("El usuario " + user + " ha actualizado los datos de la tabla " + tableName);

            System.out.println(sb.toString());
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sb.toString());
            HelperStringHistory.historyMaker("Errr/// El usuario " + user + " no ha podido actualizar los datos de la tabla " + tableName);
            dialogGenerator(assistent,1,dialogText, "NO se han actualizado los datos seleccionados");
        }

    }

    public static List<String> giverName(String tableName, TextFlow dialogText, ImageView assistent){
        List<String> nameData = new ArrayList<>();
        try (Statement st = conect.createStatement()){
            ResultSet rSt = st.executeQuery("DESCRIBE " + tableName);
            while (rSt.next()){
                nameData.add(rSt.getString(1));
            }
            return nameData;
        }catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void deleteData(String tableName, List<String> valuesToDelete, List<String> valuesName, TextFlow dialogText, ImageView assistent,String user) {

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ").append(tableName).append(" WHERE ");


        for (int i = 0; i < valuesToDelete.size(); i++) {

            sb.append(valuesName.get(i)).append(" = ");

            try {
                sb.append(Double.parseDouble(valuesToDelete.get(i)));
            }catch (NumberFormatException e){
                sb.append("'").append(valuesToDelete.get(i)).append("'");
            }

            if (i < valuesToDelete.size() - 1){sb.append(" AND ");}
            else {sb.append(";");}
        }



        try (PreparedStatement sp = conect.prepareStatement(sb.toString())){
            sp.executeUpdate();
            dialogGenerator(assistent,1,dialogText, "Se ha eliminado la data seleccionada");
            HelperStringHistory.historyMaker("El usuario " + user + " ha eliminado unos datos de la tabla " + tableName);
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sb.toString());
            HelperStringHistory.historyMaker("Errr//// El usuario " + user + "no ha podido eliminar unos datos de la tabla " + tableName);
            dialogGenerator(assistent,1,dialogText, "No se ha podido eliminar la data seleccionada");
        }

    }

    @Override
    public String dataSearch(String tableName, List<String> valuesForSearch, TableView<ObservableList<String>> tableShower, TextFlow dialogText, ImageView assistent,String user) {
        List<String> nameData = giverName(tableName, dialogText ,assistent);
        System.out.println(valuesForSearch);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(tableName);
        for (int i = 0; i < valuesForSearch.size(); i++) {
            if (!valuesForSearch.get(i).isEmpty()) {
                sb.append(" WHERE ");
                break;
            }
        }

            for (int i = 0; i < valuesForSearch.size(); i++) {
                if (!valuesForSearch.get(i).isEmpty()) {
                    sb.append(nameData.get(i)).append(" = ");
                    try {
                        sb.append(Double.parseDouble(valuesForSearch.get(i)));
                    } catch (NumberFormatException e) {
                        sb.append("'").append(valuesForSearch.get(i)).append("'");
                    }
                    try {
                        if (!valuesForSearch.get(i + 1).isEmpty()) {
                            sb.append("\n").append(" AND ");
                        }
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("final");
                    }
                }
        }


        try (Statement st = conect.createStatement()) {
            if (tableShower != null){
                tableShower.getColumns().clear();
                tableShower.getItems().clear();
            }
            if (tableShower == null) {
                tableShower = new TableView<>();
            }
            ResultSet rst = st.executeQuery("DESCRIBE " + tableName);
            while (rst.next()) {
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rst.getString(1));
                final int pos = tableShower.getColumns().size();

                column.setCellValueFactory(e -> new SimpleStringProperty( e.getValue().get(pos)));
                tableShower.getColumns().add(column);
            }
            ResultSet rSt = st.executeQuery(sb.toString());
            while (rSt.next()){
                ObservableList<String> fila = FXCollections.observableArrayList();

                for (int i = 1; i <= tableShower.getColumns().size(); i++) {
                    fila.add(rSt.getString(i));
                }

                tableShower.getItems().add(fila);
            }
            HelperStringHistory.historyMaker("El usuario " + user + " ha buscado unos datos en " + tableName);
            return sb.toString();
        }
        catch (SQLException e){
                System.err.println(e);
                System.err.println(sb.toString());
        }
        return "Something bad happened";

    }

    @Override
    public boolean deleteTables(String tableName, TextFlow dialogText, ImageView assistent,String user) {
        if (tableName.equals("datacatcheruser")){
            return false;
        }
        sql = "DROP TABLE " + tableName + ";";
        try (Statement st = conect.createStatement()){
            st.executeUpdate(sql);
            dialogGenerator(assistent,0,dialogText, "Se ha eliminado la tabla " + tableName);
            HelperStringHistory.historyMaker("El usuario " + user + " ha eliminado la tabla " + tableName);
            return true;
        }catch (SQLException e){
            System.err.println(e);
            System.err.println(sql);
        }
        return false;
    }

    public static void panesAdder(AnchorPane anchorPaneForAddTables, int valueSpin, int fish){
        Pane pane = new Pane();
        ObservableList<String> listType = FXCollections.observableArrayList("VARCHAR(50)","INTEGER","DOUBLE","BOOLEAN");
        ObservableList<String> keyType = FXCollections.observableArrayList("NOT NULL","PRIMARY KEY");

        double anchWith = anchorPaneForAddTables.getWidth();
        double anchResult = anchWith/(valueSpin);
        System.out.println(pane.getPrefWidth());

        double x = Math.min(
                fish * anchResult,
                anchorPaneForAddTables.getWidth() - pane.getPrefWidth()
        );
        double ancho = anchorPaneForAddTables.getWidth() / valueSpin;
        System.out.println(valueSpin-fish);
        System.out.println(anchResult);

        Label labelTextForThis = new Label("Data name");
        TextField dataNameField = new TextField();
        dataNameField.promptTextProperty().set("Data Name");

        labelTextForThis.setLayoutX(50);
        labelTextForThis.setLayoutY(10);

        dataNameField.setLayoutX(50);
        dataNameField.setLayoutY(25);

        ComboBox<String> comboxForTDataType = new ComboBox<>(listType);
        ComboBox<String> keyForData = new ComboBox<>(keyType);

        comboxForTDataType.setLayoutX(0);
        comboxForTDataType.setLayoutY(55);

        keyForData.setLayoutX(70);
        keyForData.setLayoutY(55);

        comboxForTDataType.setPrefWidth(ancho/2);
        keyForData.setPrefWidth(ancho/2);

        pane.getChildren().add(labelTextForThis);
        pane.getChildren().add(dataNameField);
        pane.getChildren().add(comboxForTDataType);
        pane.getChildren().add(keyForData);

        pane.setBorder(Border.stroke(Color.color(1,0,0)));
        pane.setLayoutX(x);
        pane.setLayoutY(100);
        pane.setPrefWidth(ancho);
        pane.setMaxHeight(100);

        anchorPaneForAddTables.getChildren().add(pane);

    }

    public static void autoGenerateTextFields(String titleTable, AnchorPane anh, Pane pane){
        if (pane == null) {
            sql = "DESCRIBE " + titleTable + ";";
            try (Statement st = conect.createStatement()) {
                ResultSet rSt = st.executeQuery(sql);
                int i = 80;
                while (rSt.next()) {
                    i += 30;
                    TextField tf = new TextField();
                    tf.setLayoutX(50);
                    tf.setLayoutY(i);
                    String dataN = "Tipo data: " + rSt.getString(2);
                    tf.setPromptText(dataN);
                    anh.getChildren().add(tf);
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }else if (anh == null) {
            sql = "DESCRIBE " + titleTable + ";";
            try (Statement st = conect.createStatement()) {
                ResultSet rSt = st.executeQuery(sql);
                int i = 80;
                while (rSt.next()) {
                    i += 30;
                    TextField tf = new TextField();
                    tf.setLayoutX(50);
                    tf.setLayoutY(i);
                    String dataN = "Tipo data: " + rSt.getString(2);
                    tf.setPromptText(dataN);
                    pane.getChildren().add(tf);
                }
            }
            catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public static void fillerBox(ComboBox<String> cStr){
        if (cStr != null){
            cStr.getItems().clear();
        }
        ObservableList<String> listObs = FXCollections.observableArrayList();
        try (Statement st = conect.createStatement()){
            ResultSet rSt = st.executeQuery("SHOW TABLES");
            while (rSt.next()) {
                if (rSt.getString(1).contains("dataCatcherUser")) {
                    System.out.println("se ha saltado la tabla de users");
                } else{listObs.add(rSt.getString(1));}
            }
            cStr.setItems(listObs);
        }catch (SQLException e){
            System.err.println(e);
        }

    }
    public static int numberDataColumns(String tableName){
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
    //Le pasamos tanto el nombre de la tabla como el atributo que queremos transformar, y usamos constantemente la tabla que le pasamos para dar datos y metodos...
    public static TableView<ObservableList<String>> contentTypeGiver(String titleTable, TableView<ObservableList<String>> tbW) {
        if (tbW != null) {
            tbW.getColumns().clear();
            tbW.getItems().clear();
        }
        if (tbW == null) {
            tbW = new TableView<>();
        }
        if (titleTable != null) {
            if (!titleTable.equalsIgnoreCase("datacatcheruser")) {
                try (Statement st = conect.createStatement()) {

                    ResultSet rst = st.executeQuery("DESCRIBE " + titleTable);

                    while (rst.next()) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(rst.getString(1));
                        final int position = tbW.getColumns().size();

                        column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(position)));
                        tbW.getColumns().add(column);
                    }


                    // joder, esto es mas sencillo, en el creas el objeto, le metes lo datos y se lo añades a la tableView, es una agregacion dinamica

                    ResultSet data = st.executeQuery("SELECT * FROM " + titleTable);
                    while (data.next()) {
                        //Creas la lista constantemente, y entiendo que una vez añadida, como la base de datos ve que hay datos, se lo pasa al siguiente

                        ObservableList<String> fila = FXCollections.observableArrayList();

                        for (int i = 1; i <= tbW.getColumns().size(); i++) {
                            fila.add(data.getString(i));
                        }

                        tbW.getItems().add(fila);
                    }


                    return tbW;

                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
        return null;
    }

    public static void titleGiver(TabPane nameTab){

        try (Statement st = conect.createStatement()){
            sql = "SHOW TABLES;";
            ResultSet rst = st.executeQuery(sql);
            while (rst.next()) {
                if (!rst.getString(1).equalsIgnoreCase("datacatcheruser")) {
                    Tab tabI = new Tab(rst.getString(1));
                    nameTab.getTabs().add(tabI);
                }
            }
        }catch (SQLException e){
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        int a = 0;
        try (Statement st = conect.createStatement()){
            sql = "DESCRIBE tabla4;";
            ResultSet rst = st.executeQuery(sql);
            //Primer elemento el nombre de la fila, segundo elemento, el tipo de dato
            while (rst.next()) {
                a++;
                System.out.println(rst.getString(1) + " -2- " + rst.getString(2) + " -3- " + rst.getString(3) + " -4- " + rst.getString(4) + " -5- " + rst.getString(5));
            }
            System.out.println(a);
        }catch (SQLException e){
            System.err.println(e);
        }

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
        //System.out.println(dataCount("datacatcheruser"));
    }
}
