package com.example.summerapp.WindowAndView;

import javax.swing.*;

public class Warnings {
  public static void warningJump(int i){
      switch (i){
       case 0 -> JOptionPane.showMessageDialog(null,"You could use that staff for your password instead of your user name, don't you think? (Don't use special characters)");
       case 1 -> JOptionPane.showMessageDialog(null, "Data not introduced... Are you lack of ideas?");
       case 2 -> JOptionPane.showMessageDialog(null, "Unable to show data, idk why");
       case 3 -> JOptionPane.showMessageDialog(null ,"No data available, put something");
       case 4 -> JOptionPane.showMessageDialog(null, "Delete failed (Check if the user exists or if its correct)");
       case 5 -> JOptionPane.showMessageDialog(null, "You are worng on something, so we can't edit it... yes, i breath.");
       case 6 -> JOptionPane.showMessageDialog(null, "This is the same password you putted before... Now you remember that again, you can log in");
       case 7 -> JOptionPane.showMessageDialog(null, "HEY I NEED MORE LETTERS, GIVE ME AT LEAST 8");
          default -> JOptionPane.showMessageDialog(null, "Fish (i hope no one see this one...  hi~~)");
      }
  }
}
