package com.example.summerapp.Connections;

import com.example.summerapp.Controllers.WelcomeController;
import com.example.summerapp.DataBaseInitialScheme.InitialSchemeForUser;
import com.example.summerapp.HelloApplication;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    static WelcomeController wC = new WelcomeController();
    private static final String url;

    static {
        try {
            JSONObject js1 = wC.jsonLecture("DataBaseStatus");
            url = (String) js1.get("DataBaseString");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection conect = null;

    private static String user = "root";
    private static String password = "";

    public static Connection getInstance() {
        try {
            if (conect == null) {
                conect = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión a la base de datos establecida");
            }


        }
        catch (CommunicationsException e){
            System.err.println("La dataBase no está abierta");
        }
        catch (SQLException e){
            System.err.println("No se ha conectado a la base de datos por lo que sea");
            System.err.println(e);
        }

        return conect;
    }

    public static boolean closeConn(){
        try {
            if(conect != null){
                conect.close();
                System.out.println("Conexion cerrada");
                return true;
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        getInstance();
        closeConn();
    }
}
