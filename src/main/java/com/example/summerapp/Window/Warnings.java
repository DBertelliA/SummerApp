package com.example.summerapp.Window;

import javax.swing.*;

public class Warnings {
  public static void warningJump(int i){
      switch (i){
       case 1 -> JOptionPane.showMessageDialog(null, "Data not introduced");
       case 2 -> JOptionPane.showMessageDialog(null, "Unable to show data");
       case 3 -> JOptionPane.showMessageDialog(null ,"No data available");
          default -> JOptionPane.showMessageDialog(null, "Fish");
      }
  }
}
