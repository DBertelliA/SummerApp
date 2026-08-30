package com.example.summerapp.Interface.Functions;

import com.example.summerapp.Connections.ConnectionMySQL;
import com.example.summerapp.Helper.FinderAll;
import com.example.summerapp.Interface.UserFunctionsInterface;
import com.example.summerapp.Models.User;
import com.example.summerapp.WindowAndView.Warnings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.IllegalBlockSizeException;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserFunctions implements UserFunctionsInterface {
    static Connection connect = ConnectionMySQL.getInstance();
    static String sql;
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User addUser(User user) {
        sql = "INSERT INTO dataCatcherUser VALUES (?,?)";

        try (PreparedStatement pSt = connect.prepareStatement(sql)){
            if (user.getNameSystem().isEmpty() || user.getPasswordSystem().isEmpty()){
                throw new IllegalArgumentException();
            }
            if (user.getNameSystem().matches(".*[\\[\\],._!*\"#·$%&/()=?¿'¡+\\-ç´;:].*")) {
                throw new RuntimeException();
            }
            if (user.getNameSystem().length() < 8 || user.getPasswordSystem().length() < 8){
                throw new IllegalBlockSizeException();
            }

            pSt.setString(1, user.getNameSystem());
            pSt.setString(2, encoder.encode(user.getPasswordSystem()));
            pSt.executeUpdate();

            return user;
        }catch (SQLException | IllegalArgumentException e){
            Warnings.warningJump(1);
        } catch (RuntimeException e) {
            Warnings.warningJump(0);
        } catch (IllegalBlockSizeException e) {
            Warnings.warningJump(7);
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
    public boolean deleteUser(User user) {
        User userVerif = FinderAll.findUser(user.getNameSystem());
        if(userVerif != null){
        sql = "DELETE FROM dataCatcherUser WHERE Username = ?";

        try (PreparedStatement pSt = connect.prepareStatement(sql)){
            pSt.setString(1, user.getNameSystem());

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
    public User updateUser(User user) {
        User userVerifUser = FinderAll.findUser(user.getNameSystem());
        String p = JOptionPane.showInputDialog("Introduce the password");
        while (p.isEmpty()){
            p = JOptionPane.showInputDialog("Why is empty?, just introduce the password");
        }
        User userVerifPass = FinderAll.passwordMatcher(new User(user.getNameSystem(), p));
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
                    if (user.getNameSystem().isEmpty() || user.getPasswordSystem().isEmpty()){
                        throw new IllegalArgumentException();
                    }
                    if (user.getNameSystem().matches(".*[\\[\\],._!*\"#·$%&/()=?¿'¡+\\-ç´;:].*")) {
                        throw new RuntimeException();
                    }
                    if (user.getNameSystem().length() < 8 || user.getPasswordSystem().length() < 8){
                        throw new IllegalBlockSizeException();
                    }
                    pSt.setString(1,encoder.encode(pNew));
                    pSt.setString(2, user.getNameSystem());
                    pSt.executeUpdate();
                    System.out.println("Editado:");
                    return new User(user.getNameSystem(),pNew);
                }catch (SQLException | IllegalArgumentException e){
                    Warnings.warningJump(1);
                } catch (RuntimeException e) {
                    Warnings.warningJump(0);
                } catch (IllegalBlockSizeException e) {
                    Warnings.warningJump(7);
                }
                }else {
                    Warnings.warningJump(5);
                }
            }else{
                Warnings.warningJump(5);
            }

        return null;

    }

    public static void main(String[] args) throws SQLException {
        UserFunctions i = new UserFunctions();
        i.addUser(new User("t","t"));
        //System.out.println(i.addUser(new User("d", "c")));
        //System.out.println(encoder.matches("c",""));

        //------Mostrar-------
        //System.out.println(i.showData());
        //-----eliminar-------
        //System.out.println(i.deleteUser("b"));
        //------Editar-------
        //System.out.println(i.updateUser("a")); //nueba pass: a



    }
}
