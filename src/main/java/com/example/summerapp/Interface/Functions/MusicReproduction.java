package com.example.summerapp.Interface.Functions;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicReproduction {
    static int position = 0;
    public static String mediaLine = "src/main/resources/SMResources/TestM1.wav";
    public static Media sound = new Media(new File(mediaLine).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(sound);
    static List<String> songList = songs();


    public static String getMediaLine() {
        return mediaLine;
    }
    public static void setMediaLine(String mediaLine) {
        MusicReproduction.mediaLine = mediaLine;
    }


    public static Media getSound() {
        return sound;
    }

    public static void setSound(Media sound) {
        MusicReproduction.sound = sound;
    }


    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        MusicReproduction.mediaPlayer = mediaPlayer;
    }


    public static List<String> getSongList() {
        return songList;
    }
    public static void setSongList(List<String> songList) {
        MusicReproduction.songList = songList;
    }
    

    public static void changeSongForward(){
        System.out.println(getSongList());
        if (!(position == getSongList().size())) {
            System.out.println("Entro");

            asignator(position++);

            System.out.println("cancion : " + songList.get(position-1));
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());
        }else {
            position = 0;

            asignator(position++);

            System.out.println("cancion : " + songList.get(0));
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());
        }
    }



    public static void changeSongBackward(){
        if (!(position == 0)) {
            asignator(--position);

        }else {
            position = songList.size()-1;
            asignator(position);

        }
    }

    public static void reproduction(){
        mediaPlayer.play();
    }
    public static void stopReproduction(){
        mediaPlayer.stop();
    }
    public static void pauseReproduction(){
        mediaPlayer.pause();
    }

    public static List<String> songs(){
        List<String> songInFiles = new ArrayList<>();
        File folders = new File("src/main/resources/SMResources");
        for (File folderEntry : Objects.requireNonNull(folders.listFiles())){
            if (folderEntry.isDirectory()){
                System.out.println("No se coge el direct");
            }else {
                songInFiles.add(folderEntry.getAbsolutePath());
            }
        }
        return songInFiles;
    }

    private static void asignator(int positionOf) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        setMediaLine(getSongList().get(positionOf));

        setSound(new Media(new File(getMediaLine()).toURI().toString()));

        setMediaPlayer(new MediaPlayer(getSound()));
    }


}
