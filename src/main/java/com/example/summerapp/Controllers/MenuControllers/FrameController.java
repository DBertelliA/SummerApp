package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.History.HelperStringHistory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameController {
    static Timeline executionEgg = new Timeline();
    static PauseTransition pT = new PauseTransition(Duration.seconds(1.35));

    public static void framesView(ImageView imgW, int i, boolean disable) {
        executionEgg.getKeyFrames().clear();
        System.out.println("limpio");

        if (executionEgg.getStatus() == Animation.Status.RUNNING) {
            executionEgg.stop();
        }

        if (pT.getStatus() == Animation.Status.RUNNING) {
            pT.stop();
        }

        switch (i) {
            case 0 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/reHappy.jpg"))));
            }
            case 1 -> {
                executionEgg.stop();
                executionEgg.getKeyFrames().clear();

                pT.stop();

                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/reNeutral.jpg"))));
                KeyFrame kNew = new KeyFrame(Duration.seconds(1), e -> {
                    if (disable) {
                        if (executionEgg != null && pT != null) {

                            executionEgg.stop();
                            executionEgg.getKeyFrames().clear();
                            pT.stop();

                        }
                    } else {

                        Random nRandom = new Random();
                        int r = nRandom.nextInt(1000) + 1;
                        System.out.println(r);

                        if (r == 1000) {
                            System.out.println("Entré");
                            executionEgg.stop();
                            imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Video/sneezingRemake.gif"))));
                            pT.setOnFinished(p -> {
                                framesView(imgW, 6, false);
                            });
                            pT.playFromStart();
                        }
                    }
                });
                executionEgg.getKeyFrames().add(kNew);
                executionEgg.setCycleCount(Animation.INDEFINITE);
                executionEgg.play();
            }
            case 2 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/New-Akward.jpg"))));
            }
            case 3 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/What.jpg"))));
            }
            case 4 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/YADTOP.jpg"))));
            }
            case 5 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/Mad.jpg"))));
            }
            case 6 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/reUps.jpg"))));
            }
            case 7 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/cmon.jpg"))));
            }
            default -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Expressions/reError.jpg"))));
            }
        }
    }

    public static void initAssist(TextFlow dialogText, ImageView assistent) {
        dialogText.setDisable(false);
        dialogText.setVisible(true);
        assistent.setVisible(true);
        assistent.setDisable(false);
        assistent.setManaged(true);
        assistent.setPickOnBounds(true);

        framesView(assistent,1, false);

        Font font = Font.font(20);
        Text textAssign = new Text("Hi...");

        textAssign.setFont(font);
        textAssign.setFill(Color.WHITE);

        textAssign.setTextAlignment(TextAlignment.LEFT);
        dialogText.getChildren().clear();
        dialogText.getChildren().add(textAssign);

        AtomicInteger i = new AtomicInteger();

        assistent.setOnMouseClicked(event -> {framesView(assistent, i.getAndIncrement(), false);
            System.out.println(i.get());
            String[] listDialogs = new String[]{"Hey! That tickles",
                                                "Dude, Stop...",
                                                "..."};
            if (i.get() <= 10) {
                framesView(assistent, 0, false);
                ErrorReactionsFramesController.dialogFormat(dialogText, listDialogs[0]);
            }
            if (i.get() > 10 && i.get() <= 20){
                framesView(assistent, 2, false);
                ErrorReactionsFramesController.dialogFormat(dialogText, listDialogs[1]);
            }
            if (i.get() > 20 && i.get() <= 30){
                framesView(assistent, 7, false);
                ErrorReactionsFramesController.dialogFormat(dialogText, listDialogs[2]);
            }
            if (i.get() > 30){
                HelperStringHistory.historyMaker("A user thought that it was a good idea kidding with the assistant");
                Platform.exit();
            }
        });



    }



}
