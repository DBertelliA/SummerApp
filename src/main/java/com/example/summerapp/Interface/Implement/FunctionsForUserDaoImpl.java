package com.example.summerapp.Interface.Implement;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Helper.FinderAll;
import com.example.summerapp.Interface.UserFunctionsInterface;
import com.example.summerapp.Models.User;
import com.example.summerapp.Window.Warnings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunctionsForUserDaoImpl implements UserFunctionsInterface {
    static Connection connect = ConnectionMySQL.getInstance();
    static String sql;
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User addUser(User user) {
        sql = "INSERT INTO dataCatcherUser VALUES (?,?)";

        try (PreparedStatement pSt = connect.prepareStatement(sql)){

            pSt.setString(1, user.getNameSystem());
            pSt.setString(2, encoder.encode(user.getPasswordSystem()));
                pSt.executeUpdate();
            System.out.println("Se ha introducido");
                    return user;
        }catch (SQLException e){
            Warnings.warningJump(1);
        }
        return null;
    }

    @Override
    public String showData() {
        List<User> listUser = new ArrayList<>();
        sql = "SELECT * FROM dataCatcherUser";
        try (Statement st = connect.createStatement()){
            ResultSet resultSt = st.executeQuery(sql);
            while (resultSt.next()){
                listUser.add(new User(
                        resultSt.getString(1),
                        resultSt.getString(2)
                ));
            }

            if(listUser.isEmpty()){
                throw new RuntimeException();
            }

            return listUser.toString().replaceAll("[ \\[\\],]", "");
        }catch (SQLException e){
            Warnings.warningJump(2);
        }catch (RuntimeException e){
            Warnings.warningJump(3);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String name) {
        User userVerif = FinderAll.findUser(name);
        if(userVerif != null){
        sql = "DELETE FROM dataCatcherUser WHERE Username = ?";

        try (PreparedStatement pSt = connect.prepareStatement(sql)){
            pSt.setString(1, name);

            pSt.executeUpdate();
            System.out.println("Se ha eliminado");
            return true;
        }catch (SQLException e){
            Warnings.warningJump(4);
            return false;
            }
        } else {return false;}
    }

    @Override
    public User editUser(String user) {
        User userVerifUser = FinderAll.findUser(user);
        String p = JOptionPane.showInputDialog("Introduce the password");
        while (p.isEmpty()){
            p = JOptionPane.showInputDialog("Why is empty?, just introduce the password");
        }
        User userVerifPass = FinderAll.passwordMatcher(new User(user, p));
        String pNew = JOptionPane.showInputDialog("Introduce the NEW password");
            if(userVerifUser != null){
                while (pNew.isEmpty()){
                    pNew = JOptionPane.showInputDialog("No an empty field pls (Just introduce the new password)");
                }
                if (userVerifPass != null){
                    if (p.matches(pNew)){
                    Warnings.warningJump(6);
                    return userVerifPass;
                    }
                sql = """
                        UPDATE dataCatcherUser 
                        set UserPassword = ?
                        WHERE Username = ?;
                        """;
                try (PreparedStatement pSt = connect.prepareStatement(sql)){
                    pSt.setString(1,encoder.encode(pNew));
                    pSt.setString(2, user);
                    pSt.executeUpdate();
                    System.out.println("Editado:");
                    return new User(user,pNew);
                }catch (SQLException e){
                    System.err.println(e);
                }
                }else {
                    Warnings.warningJump(5);
                }
            }else{
                Warnings.warningJump(5);
            }

        return null;

    }

    //Creo que a la hora de hacer las tablas, deberia hacerles una columna extra obligatoria, que al ser comparado en este metodo, lea ciertos valores
    //los cuales los usuarios... o podria meter a los usuarios en una lista interna, la cual, si la lista interna coincide con las lista permitida en la que está dicha tabla...
    //tengo que pensarlo (Por ahora, grupos de trabajo)
    @Override
    public boolean limitsForUsers() {
        return false;
    }

    @Override
    public boolean tutorialValue(boolean tutorial) {
        return !tutorial;
    }

    public static void main(String[] args) throws SQLException {
        FunctionsForUserDaoImpl i = new FunctionsForUserDaoImpl();
        //System.out.println(i.addUser(new User("d", "c")));
        //System.out.println(encoder.matches("c",""));

        //------Mostrar-------
        //System.out.println(i.showData());
        //-----eliminar-------
        //System.out.println(i.deleteUser("b"));
        //------Editar-------
        //System.out.println(i.editUser("a")); //nueba pass: a



    }
}
