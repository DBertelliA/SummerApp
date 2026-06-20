package com.example.summerapp.WindowAndView;

import javax.swing.*;

public class Warnings {
  public static void warningJump(int i){
      switch (i){
       case 1 -> JOptionPane.showMessageDialog(null, "Data not introduced DAMN...");
       case 2 -> JOptionPane.showMessageDialog(null, "Unable to show data, idk why");
       case 3 -> JOptionPane.showMessageDialog(null ,"No data available, put something");
       case 4 -> JOptionPane.showMessageDialog(null, "Delete failed (Check if the user exists or if its correct)");
       case 5 -> JOptionPane.showMessageDialog(null, "You are worng on something, so we can't edit it... yes, i breath.");
       case 6 -> JOptionPane.showMessageDialog(null, "This is the same password... do you breath?");
          default -> JOptionPane.showMessageDialog(null, "Fish (i hope no one see this one...  hi~~)");
      }
  }
}
