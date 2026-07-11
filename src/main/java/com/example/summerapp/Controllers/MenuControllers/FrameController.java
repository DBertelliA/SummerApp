package com.example.summerapp.Controllers.MenuControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

import java.util.Objects;

public class FrameController {
    static M_addFunctions m_A = new M_addFunctions();
    public static void framesView(ImageView imgW, int i) {
        switch (i) {
            case 0 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Happy-new.jpg"))));
            }
            case 1 -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Neutral-new.jpg"))));
            }
            default -> {
                imgW.setImage(new Image(Objects.requireNonNull(FrameController.class.getResourceAsStream("/Sprites/Error.jpg"))));
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

        m_A.initDialog(dialogText);
        framesView(assistent,0);

        assistent.setOnMouseClicked(event -> FrameController.frameChangerMainMenu(dialogText, assistent));
    }

    private static void frameChangerMainMenu(TextFlow dialogText, ImageView assistent) {
        if (m_A.counter > 2) {
            m_A.counter = 0;
            FrameController.framesView(assistent,0);
        }
        System.out.println("contador: " + m_A.counter);
        if (m_A.counter < m_A.dialogs.length-1){
            if (m_A.counter == -1) {
                FrameController.framesView(assistent,0);
                m_A.counter++;
            }else {
                FrameController.framesView(assistent,m_A.counter);
                m_A.counter++;
            }
            m_A.initDialog(dialogText);
        }
    }

}
