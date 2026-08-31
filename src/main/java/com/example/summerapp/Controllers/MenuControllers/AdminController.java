package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.HelloApplication;
import com.example.summerapp.Interface.Functions.UserFunctions;
import com.example.summerapp.Interface.UserFunctionsInterface;
import com.example.summerapp.Models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    UserFunctionsInterface uFI = new UserFunctions();
    @FXML public Label welcomeAdmin;

    @FXML
    public Button deleteUser;

    @FXML
    public Button editUserPassword;

    @FXML
    public Button backButton;

    @FXML
    public ListView<String> listUsers = new ListView<>();

    @FXML
    public Label selectedData;

    static Connection connect = ConnectionMySQL.getInstance();

    static String userName;
    static String userPassword;

    public void listUsersSetter(){
        deleteUser.setDisable(true);
        editUserPassword.setDisable(true);

        try (PreparedStatement sPT = connect.prepareStatement("SELECT * FROM dataCatcherUser;")){
            ResultSet rST = sPT.executeQuery();
            List<String> userList = new ArrayList<>();

            while (rST.next()){
                userList.add( rST.getString(1) + " |///&///| " + rST.getString(2));
            }

            listUsers.getItems().setAll(FXCollections.observableArrayList(userList));

            listUsers.setCellFactory(listCom -> {
                ListCell<String> cell = new ListCell<>();
                cell.textProperty().bind(cell.itemProperty());
                return cell;
            });

            listUsers.setOnMouseClicked( e -> {
                String spt = listUsers.getSelectionModel().getSelectedItem();
                if (spt != null) {
                    String[] doub = spt.split("\\|///&///\\|");
                    userName = doub[0];
                    userPassword = doub[1];
                    selectedData.setText("You have selected: " + "Name: " + userName + "PW: Is hardcoded for something");
                    deleteUser.setDisable(false);
                    editUserPassword.setDisable(false);
                }else{
                    deleteUser.setDisable(true);
                    editUserPassword.setDisable(true);
                }
            });
        }catch (SQLException e){
            System.err.println(e);
        }


    }
    public void backB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) welcomeAdmin.getScene().getWindow();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteFunction(){
        uFI.deleteUser(new User(userName,userPassword));
    }

    public void updateFunction(){
        uFI.updateUser(new User(userName,userPassword));
    }

}
