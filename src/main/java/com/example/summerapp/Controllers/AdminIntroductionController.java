package com.example.summerapp.Controllers;

import javax.swing.*;

public class AdminIntroductionController {
    String nameAdmin;
    String passAdmin;

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }

    public void credentialChanger() {
        JOptionPane.showMessageDialog(null, "This should be only visible in the beginning of the data base");
        JOptionPane.showMessageDialog(null, "You are free to put what ever password and userName you want");
        JOptionPane.showMessageDialog(null, "I recommend to put specials characters or something under 8 words long (You aren't limited, but you can't let it empty)");
        JOptionPane.showMessageDialog(null, "If you forget about your credentials, the admin pane is fully block until you remember");
        JOptionPane.showMessageDialog(null, "THERE IS NO WAY TO CHANGE THE PASSWORD AND USER NAME, YOU KNOW THE ONLY SOLUTION IN THAT CASE");


        while (getNameAdmin() == null || getNameAdmin().isEmpty()){
            setNameAdmin(JOptionPane.showInputDialog("User"));
        }
        while (getPassAdmin() == null || getPassAdmin().isEmpty()) {
            setPassAdmin(JOptionPane.showInputDialog("Password"));
        }
    }

}
