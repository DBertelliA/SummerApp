package com.example.summerapp.Controllers;

import com.example.summerapp.Controllers.MenuControllers.AdminController;
import com.example.summerapp.Controllers.MenuControllers.MenuController_All;
import com.example.summerapp.HelloApplication;
import com.example.summerapp.Helper.FinderAll;
import com.example.summerapp.History.HelperStringHistory;
import com.example.summerapp.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class LogInController {
    AdminIntroductionController aIC = new AdminIntroductionController();
    @FXML
    public Label confirmText;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    protected void loginButton() {
        String privateAdminName = aIC.lectureCredential()[0];
        String privateAdminPassword = aIC.lectureCredential()[1];
            if (user.getText().equals(privateAdminName) && password.getText().equals(privateAdminPassword)){
                adminButton();
            }else {
                if(FinderAll.passwordMatcher(new User(user.getText(), password.getText())) != null){
                confirmText.setText("Usuario correcto");
                HelperStringHistory.historyMaker("El usuario: " + user.getText() + " ha accedido a la database");
                try {
                    FXMLLoader fxmload = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));

                    Scene sceneLoad = new Scene(fxmload.load(), 910, 600);

                    MenuController_All mc = fxmload.getController();
                    mc.userLoggedMenu.setText(user.getText());
                    mc.inicializate();

                    Stage staging = (Stage) confirmText.getScene().getWindow();
                    staging.setScene(sceneLoad);

                    staging.show();

                } catch (IOException e) {
                    System.err.println(e);
                }
                }else {
                    confirmText.setText("Usuario o contraseña no valido");
                    user.clear();
                    password.clear();
                }
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
        String mgW = aIC.lectureCredential()[2];
        String sp1 = JOptionPane.showInputDialog("Introduce the password \"Mr admin\" ");
        if (sp1 != null) {
            if (sp1.equals(mgW)) {
                JOptionPane.showMessageDialog(null, "My bad pall");
                HelperStringHistory.historyMaker("El administrador ha aparecido! Deberia serlo...");
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
                user.clear();
                password.clear();
            }
        }
    }

    public void test1(){
        aIC.credentialSetter();
    }

}
