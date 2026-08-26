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

    public int count = 1;

    public int getCount() {return count;}

    public void setCount(int count) {this.count = count;}

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
                        //Same name as other table
                        String[] dialogs = new String[]{"Hey...",
                                "There is other table with the same name, so...",
                                "The table wasn't added",
                                "You know...",
                                "I didn't spend hours making a place where you can see all the tables for nothing...",
                                "..."};
                        FrameController.framesView(assistant, 999, false);
                        setCount(dialogs.length);

                        dialogFormat(text, dialogs[count]);

                    }
                    case 2 -> {
                        //Some fields weren't completed
                        String[] dialogs = new String[]{"Hey...",
                                "Just a little reminder...",
                                "you need the to complete all the fields for something like",
                                "idk",
                                "create a table?",
                                "like...",
                                "ik... i unable the fields after you press the \"next\" button",
                                "but to be honest...",
                                "we know you did that on purpose",
                                "no one can be that dumb...",
                                "...right?",
                                "..."};
                        FrameController.framesView(assistant, 999, false);
                        setCount(dialogs.length);

                        dialogFormat(text, dialogs[count]);
                        break;

                    }

                    case 3 -> {
                        //Some combox weren't completed
                        String[] dialogs = new String[]{"woah...",
                                "you were going to fast",
                                "be careful next time and don't forget to fill all the fields",
                                "also... sometimes, even if you didn't fill the field or fields you just jumped",
                                "we can create create tables the null...",
                                "..."};
                        FrameController.framesView(assistant, 999, false);
                        setCount(dialogs.length);

                        dialogFormat(text, dialogs[count]);
                        break;
                    }

                    case 4 -> {
                        //Name of the table wasn't set
                        String[] dialogs = new String[]{"...",
                                "You have to be kidding me...",
                                "You know what you did, don't spect me to tell you what is wrong",
                                "You know exactly what is the problem",
                                "..."};
                        FrameController.framesView(assistant, 5, false);
                        setCount(dialogs.length);

                        dialogFormat(text, dialogs[count]);
                        break;


                    } default -> {
                        assistant.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/EasterEggs/KatSmoking.jpg"))));
                        dialogFormat(text, "Generic error of addTables");
                        break;
                    }
                }
            }
            case 2 ->{ //Edit data
                switch (getNumberErr()){
                 case 1 -> {
                     //data no correct
                     String[] dialogs = new String[]{"...mmmm...",
                             "I think, you just didn't put the correct format of the data",
                             "I mean...",
                             "In the fields, i specify the type of data you need...",
                             "so",
                             "What are you doing?",
                             "..."};
                     FrameController.framesView(assistant, 5, false);
                     setCount(dialogs.length);

                     dialogFormat(text, dialogs[count]);
                     break;

                 }
                }
            }
            case 3 ->{ //Search data !Not found


            }
            case 4 -> { //Delete data !Not found


            }
            case 5 -> { //You have records already open
                switch (getNumberErr()){
                    case 1 ->{
                        String[] dialogs = new String[]{"You already have that open.",
                                "Don't click on the button again until it's closed",
                        "..."};
                        FrameController.framesView(assistant, 4, false);

                        setCount(dialogs.length);
                        dialogFormat(text, dialogs[count]);
                        break;

                    }
                }
            }
            default -> {
                assistant.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/EasterEggs/KatSmoking.jpg"))));
                dialogFormat(text, "Generic error");
                break;
            }
        }

    }

    public static void dialogFormat(TextFlow text, String dialogs) {
        Text textTalk = new Text(dialogs);

        textTalk.setFont(new Font(20));
        textTalk.setFill(Color.WHITE);

        textTalk.setTextAlignment(TextAlignment.LEFT);
        text.getChildren().clear();
        text.getChildren().add(textTalk);
    }

}
