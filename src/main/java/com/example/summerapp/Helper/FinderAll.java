package com.example.summerapp.Helper;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinderAll {
    static Connection connect =  ConnectionMySQL.getInstance();
    static String sql;
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User findUser(String nameUser){

        User userClone = null;
        sql = "SELECT * FROM dataCatcherUser WHERE Username = ?";

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

    public static User passwordMatcher(User user){
        User userVerif = FinderAll.findUser(user.getNameSystem());
        if (userVerif != null){
            sql  = "SELECT * FROM dataCatcherUser WHERE Username = ?";
            try (PreparedStatement pSt = connect.prepareStatement(sql)){
                pSt.setString(1, user.getNameSystem());

                ResultSet rsT = pSt.executeQuery();
                while (rsT.next()) {
                    if (encoder.matches(user.getPasswordSystem(), rsT.getString(2))){
                        return user;
                    }
                }
            }catch (SQLException e){
                System.err.println(e);
            }

        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(findUser("a"));
        System.out.println(passwordMatcher(new User("a","b")));
    }
}
