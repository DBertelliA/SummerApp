package com.example.summerapp.Controllers.MenuControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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

    public static void framesView(ImageView imgW, int i) {
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
                    Random nRandom = new Random();
                    int r = nRandom.nextInt(1000)+1;
                    System.out.println(r);

                    if (r == 1000){
                        System.out.println("Entré");
                        executionEgg.stop();
                        imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Video/sneezingRemake.gif"))));
                        pT.setOnFinished(p -> {
                            framesView(imgW, 6);
                        });
                        pT.playFromStart();
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

        framesView(assistent,1);

        Font font = Font.font(20);
        Text textAssign = new Text("Hi...");

        textAssign.setFont(font);
        textAssign.setFill(Color.WHITE);

        textAssign.setTextAlignment(TextAlignment.LEFT);
        dialogText.getChildren().clear();
        dialogText.getChildren().add(textAssign);

        AtomicInteger i = new AtomicInteger();

        assistent.setOnMouseClicked(event -> {framesView(assistent, i.getAndIncrement());
            if(i.get() >= 8){
                i.set(0);
            }
        });



    }



}
