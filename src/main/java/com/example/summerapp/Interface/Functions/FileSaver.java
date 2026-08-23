package com.example.summerapp.Interface.Functions;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSaver {
    public static void chooser() {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Select the music you want to add...");

        FileChooser.ExtensionFilter extFilt = new FileChooser.ExtensionFilter(".Wav files (*.wav)", "*.wav");
        fileChoose.getExtensionFilters().add(extFilt);

        File audioFile = fileChoose.showOpenDialog(null);
        if (audioFile != null){
            fileCopyM(audioFile.getPath(), "src/main/resources/SMResources/");
            MusicReproduction.songs();
            MusicReproduction.setPosition(2);
            MusicReproduction.asignator(1);
            System.out.println("Archivo añadido");
        }

    }
    public static void fileCopyM(String source, String destination){
        Path in = Paths.get(source);
        Path out = Paths.get(destination);
        try {

            Files.copy(in, out.resolve(in.getFileName()), StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e ){
            System.err.println(e);
            System.err.println("There was an error trying to copy the files");
        }

    }
}
