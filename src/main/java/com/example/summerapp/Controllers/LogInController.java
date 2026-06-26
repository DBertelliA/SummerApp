package com.example.summerapp.Controllers;

import com.example.summerapp.Controllers.MenuControllers.MenuController_All;
import com.example.summerapp.HelloApplication;
import com.example.summerapp.Helper.FinderAll;
import com.example.summerapp.Interface.Functions.TablesFunctions;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import com.example.summerapp.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    TablesAutoCreateAndFuntions tACAF = new TablesFunctions();

    @FXML
    private Label confirmText;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;


    @FXML
    protected void loginButton() {
        if(FinderAll.passwordMatcher(new User(user.getText(), password.getText())) != null){
            confirmText.setText("Usuario correcto");
            try {
                FXMLLoader fxmload = new FXMLLoader(HelloApplication.class.getResource("tests.fxml"));

                Scene sceneLoad = new Scene(fxmload.load(), 910, 600);

                MenuController_All mc = fxmload.getController();
                mc.userLoggedMenu.setText(user.getText());
                mc.inicializate();

                Stage staging = (Stage) confirmText.getScene().getWindow();
                staging.setScene(sceneLoad);

                staging.show();

            }catch (IOException e){
                System.err.println(e);
            }

        }
        else {
            confirmText.setText("Usuario no valido");
        }
    }

    @FXML
    protected void signInButton() {
        try {
            FXMLLoader fxmload = new FXMLLoader(HelloApplication.class.getResource("Signin.fxml"));
            Scene sceneLoad = new Scene(fxmload.load(), 600, 400);

            SignInController sgin = fxmload.getController();
            sgin.confirLabel.setText("...");
            confirmText.setText("...");
            Stage staging = (Stage) confirmText.getScene().getWindow();
            staging.setScene(sceneLoad);

            staging.show();
        }catch (IOException e){
            System.err.println(e);
        }
    }

}
