package com.example.summerapp.Window;

import javax.swing.*;

public class Warnings {
  public static void warningJump(int i){
      switch (i){
       case 1 -> JOptionPane.showMessageDialog(null, "Data not introduced");
       case 2 -> JOptionPane.showMessageDialog(null, "Unable to show data");
       case 3 -> JOptionPane.showMessageDialog(null ,"No data available");
       case 4 -> JOptionPane.showMessageDialog(null, "Delete failed (Check if the user exists)");
       case 5 -> JOptionPane.showMessageDialog(null, "The user not exists, we can't edit it... DHAaa... ");
          default -> JOptionPane.showMessageDialog(null, "Fish");
      }
  }
}
