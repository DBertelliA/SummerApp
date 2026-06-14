package com.example.summerapp.Interface.Implement;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Helper.Finder;
import com.example.summerapp.Interface.UserFunctionsInterface;
import com.example.summerapp.Models.User;
import com.example.summerapp.Window.Warnings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        User userVerif = Finder.findUser(name);
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
    public User editUser(User user) {
        return null;
    }

    @Override
    public boolean limitsForUsers() {
        return false;
    }

    @Override
    public boolean tutorialValue() {
        return false;
    }

    public static void main(String[] args) throws SQLException {
        FunctionsForUserDaoImpl i = new FunctionsForUserDaoImpl();
        //System.out.println(i.addUser(new User("d", "c")));
        //System.out.println(encoder.matches("c",""));

        //------Mostrar-------
        //System.out.println(i.showData());
        //-----eliminar-------
        System.out.println(i.deleteUser("b"));
    }
}
