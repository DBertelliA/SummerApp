package com.example.summerapp.Controllers.MenuControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FrameController {
    public void framesView(ImageView imgW, int i) {
        switch (i) {
            case 0 -> imgW.setImage(new Image("Happy-Frame.jpg"));
            case 1 -> imgW.setImage(new Image("Neutral-Frame.jpg"));
            default -> imgW.setImage(new Image("Error.jpg"));
        }
    }
}
