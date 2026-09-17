package com.example.summerapp.Controllers.MenuControllers;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class M_addFunctions {
public int counter = 0;

public String[] dialogs = {"Hi!","I'm a cat assistant"
        , "I will try to help in you creating or adding things"};

    //nueva formato de textFlont y demas para el flow, está entretenido...

    public void initDialog(TextFlow dialogText){
        try {
            Font font = Font.font(20);
            Text textAssign;
            textAssign = new Text(dialogs[counter]);

            textAssign.setFill(Color.WHITE);
            textAssign.setFont(font);

            dialogText.setTextAlignment(TextAlignment.LEFT);

            //new Insets(arriba, derecha, abajo, izquierda)
            dialogText.setPadding(new Insets(10, 0, 0, 0));
            dialogText.getChildren().clear();
            dialogText.getChildren().add(textAssign);


            if (counter == dialogs.length - 1) {
                counter = -1;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error en el contador, raro");
        }

    }
}
