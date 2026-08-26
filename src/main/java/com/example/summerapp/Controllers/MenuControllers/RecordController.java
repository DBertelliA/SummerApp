package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecordController {
    static Stage stage = new Stage();
    public static void inRecord(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RecordsView.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 700);
            //Stage stage1 = (Stage) userLoggedMenu.getScene().getWindow();

            stage.setTitle("Record");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
    public static void closeWin(){
        stage.close();
    }

}
