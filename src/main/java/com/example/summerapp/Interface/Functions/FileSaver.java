package com.example.summerapp.Interface.Functions;

import javafx.stage.FileChooser;

import java.io.File;

public class FileSaver {
    public static void chooser() {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Select the music you want to add...");

        FileChooser.ExtensionFilter extFilt = new FileChooser.ExtensionFilter(".Wav files (*.wav)", "*.wav");
        fileChoose.getExtensionFilters().add(extFilt);

        File audioFile = fileChoose.showOpenDialog(null);
//        if (audioFile != null){ si, funciona como pienso
//            Me falta la copia del archivo
//        }

    }
}
