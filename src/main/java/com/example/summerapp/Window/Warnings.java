package com.example.summerapp.Window;

import javax.swing.*;

public class Warnings {
  public static void warningJump(int i){
      switch (i){
       case 1 -> JOptionPane.showMessageDialog(null, "Data not introduced");

          default -> JOptionPane.showMessageDialog(null, "Fish");
      }
  }
}
