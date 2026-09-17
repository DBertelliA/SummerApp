package com.example.summerapp.DataBaseInitialScheme;

import com.example.summerapp.Connections.ConnectionMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitialSchemeForUser {
    static String sql;
    static Connection connect = ConnectionMySQL.getInstance();

    public static void initDataCatch() {

        sql = """
              CREATE TABLE IF NOT EXISTS dataCatcherUser(
                Username VARCHAR(255) PRIMARY KEY,
                UserPassword VARCHAR(255) NOT NULL
              );
              """;
        try (Statement st = connect.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Executed");
        }catch (SQLException e){
            System.err.println(e);
        }
    }


}
