package com.example.summerapp.Controllers;

import com.example.summerapp.HelloApplication;
import com.example.summerapp.Helper.FinderAll;
import com.example.summerapp.History.HelperStringHistory;
import com.example.summerapp.Interface.Functions.UserFunctions;
import com.example.summerapp.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    UserFunctions functionsU = new UserFunctions();
    @FXML
    public Label confirLabel;

    @FXML
    private TextField userSg;

    @FXML
    private PasswordField passwordSg;

    @FXML
    protected void createAcc(){
        if(FinderAll.findUser(userSg.getText()) == null){
            if (functionsU.addUser(new User(userSg.getText(), passwordSg.getText()))) {
                confirLabel.setText("User added");
                HelperStringHistory.historyMaker("The user : " + userSg.getText() + " has been created");
            }else {
                confirLabel.setText("User not added");
            }
            userSg.clear();
            passwordSg.clear();
        }else {
            confirLabel.setText("The user couldn't been added");
        }

    }
    @FXML
    protected void goBack(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) confirLabel.getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        }catch (IOException e){
            System.err.println(e);
        }
    }

}
