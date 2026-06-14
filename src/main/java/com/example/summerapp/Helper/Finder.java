package com.example.summerapp.Helper;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Finder {

    public static User findUser(String nameUser){
        Connection connect =  ConnectionMySQL.getInstance();
        User userClone = null;
        String sql = "SELECT * FROM dataCatcherUser WHERE Username = ?";

        try (PreparedStatement pSt = connect.prepareStatement(sql)){
            pSt.setString(1, nameUser);

            ResultSet rsT = pSt.executeQuery();
            while (rsT.next()) {
                userClone = new User(rsT.getString(1), rsT.getString(2));
                return userClone;
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(findUser("a"));
    }
}
