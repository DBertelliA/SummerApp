package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RecordController {

    @FXML
    public ListView<String> listRecord;

    static Stage stage = new Stage();

    public void inRecord(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RecordsView.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            //Stage stage1 = (Stage) userLoggedMenu.getScene().getWindow();

            RecordController controller = fxmlLoader.getController();
            controller.refresherRecord();
            stage.setTitle("Record");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }
    public void closeWin(){
        stage.close();
    }

    public void refresherRecord(){
        try (FileReader fileReader = new FileReader("src/main/resources/Record/DataBaseHistory.txt")){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            listRecord.getItems().clear();

            List<String> listLines = new ArrayList<>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                listLines.add(line);
            }

            //We set the items
            listRecord.getItems().setAll(FXCollections.observableList(listLines));
            //They are now in the cells, but they are not been shown

            //We have to take the cell, whose contents are 2 shapes, 1 which is the one that is showing, 2 the one which has the text
            //I think "bind" is the method used to make those shapes into 1 forever
            listRecord.setCellFactory( listLand ->{
                ListCell<String> cell = new ListCell<>();
                cell.textProperty().bind(cell.itemProperty());
                return cell;
            });


        } catch (IOException e) {
            System.err.println("No se encontró el archivo");
        }
    }

}
