package com.example.summerapp.Controllers;

import com.example.summerapp.Controllers.MenuControllers.AdminController;
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

import javax.swing.*;
import java.io.IOException;

public class LogInController {
    @FXML
    public Label confirmText;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;


    @FXML
    protected void loginButton() {
        if(FinderAll.passwordMatcher(new User(user.getText(), password.getText())) != null){
            confirmText.setText("Usuario correcto");
            try {
                FXMLLoader fxmload = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));

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
            confirmText.setText("Usuario o contraseña no valido");
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

    @FXML
    protected void adminButton() {
        String sp1 = JOptionPane.showInputDialog("Introduce the password \"Mr admin\" ");
        if (sp1 != null) {
            if (sp1.equals("data1")) {
                JOptionPane.showMessageDialog(null, "My bad pall");
                try {
                    FXMLLoader fxmload = new FXMLLoader(HelloApplication.class.getResource("Admin.fxml"));
                    Scene sceneLoad = new Scene(fxmload.load(), 600, 400);

                    AdminController admin = fxmload.getController();
                    admin.welcomeAdmin.setText("Welcome administrator... it should be");
                    admin.selectedData.setText("...");
                    confirmText.setText("...");
                    admin.listUsersSetter();
                    Stage staging = (Stage) confirmText.getScene().getWindow();
                    staging.setScene(sceneLoad);

                    staging.show();
                } catch (IOException e) {
                    System.err.println(e);
                }

            } else {
                JOptionPane.showMessageDialog(null, "no, try again (If you are the admin, of course)");
            }
        }
    }

}
