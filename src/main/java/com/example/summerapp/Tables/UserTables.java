package com.example.summerapp.Tables;

import com.example.summerapp.Connections.ConnectionMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTables {
    static String sql;
    static Connection connect = ConnectionMySQL.getInstance();

    public static void main(String[] args) {

        sql = """
              CREATE TABLE IF NOT EXISTS dataCatcherUser(
                Username VARCHAR(20) PRIMARY KEY,
                UserPassword VARCHAR(255) NOT NULL
              );
              """;
        try (Statement st = connect.createStatement()){
            st.executeUpdate(sql);
            System.out.println("Ejecutado");
        }catch (SQLException e){
            System.err.println(e);
        }
    }


}
