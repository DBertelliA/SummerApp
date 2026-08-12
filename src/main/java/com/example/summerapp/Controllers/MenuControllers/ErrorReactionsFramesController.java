package com.example.summerapp.Controllers.MenuControllers;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ErrorReactionsFramesController {
    public int whereErr = 1;

    public int numberErr;

    public void setNumberErr(int numberErr) {
        this.numberErr = numberErr;
    }

    public void setWhereErr(int whereErr) {
        this.whereErr = whereErr;
    }

    public int getWhereErr() {
        return whereErr;
    }

    public int getNumberErr() {
        return numberErr;
    }

    public void errorWarning(TextFlow text, ImageView assistant, int count){
        switch (getWhereErr()){
            case 1 ->{ //AddTable or data
                switch (getNumberErr()){
                    case 1 -> {
                        assistant.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/EasterEggs/KatSmoking.jpg"))));
                        //Same name as other table
                        String[] dialogs = new String[]{"Hey...",
                                "There is other table with the same name, so...",
                                "The table wasn't added",
                                "You know...",
                                "I didn't spend hours making a place where you can see all the tables for nothing...",
                                "..."};
                        FrameController.framesView(assistant, 999);

                        Text textTalk = new Text(dialogs[count]);

                        textTalk.setFont(new Font(20));
                        textTalk.setFill(Color.WHITE);

                        textTalk.setTextAlignment(TextAlignment.LEFT);
                        text.getChildren().clear();
                        text.getChildren().add(textTalk);
                        break;

                    }
                    case 2 -> {
                        //Some fields weren't completed

                    }

                    case 3 -> {
                        //Some combox weren't completed


                    }

                    case 4 -> {
                        //Name of the table wasn't set


                    } default -> {
                        assistant.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/EasterEggs/KatSmoking.jpg"))));
                        Text textTalk = new Text("Generic error");

                        textTalk.setFont(new Font(20));
                        textTalk.setFill(Color.WHITE);

                        textTalk.setTextAlignment(TextAlignment.LEFT);
                        text.getChildren().clear();
                        text.getChildren().add(textTalk);
                        break;
                    }
                }
            }
            case 2 ->{ //Edit data


            }
            case 3 ->{ //Search data


            }
            case 4 -> { //Delete data


            }
        }

    }

}
