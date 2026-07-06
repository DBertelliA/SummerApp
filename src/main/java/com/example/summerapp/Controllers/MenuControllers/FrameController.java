package com.example.summerapp.Controllers.MenuControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class FrameController {
    public void framesView(ImageView imgW, int i) {
        switch (i) {
            case 0 -> {
                imgW.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Happy-new.jpg"))));
            }
            case 1 -> {
                imgW.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Neutral-new.jpg"))));
            }
            default -> {
                imgW.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Error.jpg"))));
            }
        }
    }
}
