package com.example.summerapp.Controllers;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.DataBaseInitialScheme.InitialSchemeForUser;
import com.example.summerapp.HelloApplication;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WelcomeController {
    static JSONParser jsoP = new JSONParser();
    static FileReader readerFl;
    static JSONObject jsonO;

    static {
        try {
            readerFl = new FileReader("src/main/resources/Record/memory(Beta).json");
            jsonO = (JSONObject) jsoP.parse(readerFl);
        } catch (IOException | ParseException e) {
            System.err.println(e);
        }
    }



    static Connection connect = null;
    @FXML public Button initDataBase;
    @FXML private Label welcomeLabel;

    public String startButton(){
        try {
            JSONObject json1 = jsonLecture("DataBaseStatus");


            if(!(boolean) json1.get("started")){
                connectSequenceNewString();
                JOptionPane.showMessageDialog(null, "Press again the start button so, we can start");
                return (String) json1.get("DataBaseString");
            }

            connect = ConnectionMySQL.getInstance();
            InitialSchemeForUser.initDataCatch();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            Scene scene = null;

                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException ex) {
                    System.err.println(ex);
                }

                LogInController lg = fxmlLoader.getController();
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setTitle("Log in");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            return (String) json1.get("DataBaseString");
        } catch (IOException | ParseException e) {
            System.err.println(e);
        }
        return null;
    }

    public void connectSequenceNewString() throws IOException, ParseException {
        JSONObject json1 = jsonLecture("DataBaseStatus");

        if(!(boolean) json1.get("started")){
            String word = "jdbc:mysql://localhost:3306/" + sequenceOfQuestions() + "?serverTimezone=UTC";
            json1.put("DataBaseString", word);
            json1.put("started", true);
        }
        if (!(boolean) json1.get("adminSet")){
            AdminIntroductionController adC = new AdminIntroductionController();
            adC.credentialSetter();
            json1.put("adminSet", true);
        }
        try (FileWriter fr = new FileWriter("src/main/resources/Record/memory(Beta).json")){
            fr.write(jsonO.toJSONString());
        }
    }

    public JSONObject jsonLecture(String place) throws IOException, ParseException {
        JSONArray jArray = (JSONArray) jsonO.get(place);

        return (JSONObject) jArray.get(0);
    }

    public String sequenceOfQuestions(){
        JOptionPane.showMessageDialog(null, "Hi");
        JOptionPane.showMessageDialog(null, "I see it's your first time here");
        JOptionPane.showMessageDialog(null, "So, lets create you personal dataBase");
        String n = "";
        while(n.isEmpty()) {
            n = JOptionPane.showInputDialog(null, "Put the name of your dataBase, (Nothing empty field or cancel button pls)");
        }
        JOptionPane.showMessageDialog(null, "Cool, lets continue");
        JOptionPane.showMessageDialog(null, "Now, you will be asked for your admin credentials");

        connect = ConnectionMySQL.getInstance();

        try (PreparedStatement st = connect.prepareStatement("CREATE DATABASE " + n)){
            st.executeUpdate();
             try (PreparedStatement st2 = connect.prepareStatement("USE " + n)) {
                st2.executeUpdate();
             }


        } catch (SQLException e) {
            System.err.println(e);
        }

        return n;
    }
}
