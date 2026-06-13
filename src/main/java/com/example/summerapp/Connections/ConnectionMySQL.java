package com.example.summerapp.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private static String url = "jdbc:mysql://localhost:3306/tableForUser?serverTimezone=UTC";
    private static Connection conect = null;

    private static String user = "root";
    private static String password = "";

    public static Connection getInstance() {
        try{
            if(conect == null){
             conect = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión a la base de datos establecida");
            }
        }catch (SQLException e){
            System.err.println("No se ha conectado a la base de datos por lo que sea");
            System.err.println(e);
        }
        return conect;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
