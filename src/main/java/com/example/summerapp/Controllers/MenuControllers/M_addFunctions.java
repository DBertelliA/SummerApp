package com.example.summerapp.Controllers.MenuControllers;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class M_addFunctions {

    //nueva formato de textFlont y demas para el flow, está entretenido...

    public void initDialog(TextFlow dialogText){
        Font font = Font.font(20);
        Text textAssign = new Text("""
                Hi!
                I'm your assistant, I will try to help if you need a guide on the creation of the tables
                or inserting data
                """);

        textAssign.setFill(Color.WHITE);
        textAssign.setFont(font);


        dialogText.setTextAlignment(TextAlignment.LEFT);
        //new Insets(arriba, derecha, abajo, izquierda)
        dialogText.setPadding(new Insets(10,0,0,0));
        dialogText.getChildren().clear();
        dialogText.getChildren().add(textAssign);
    }
}
