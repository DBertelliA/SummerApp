package com.example.summerapp.Interface.Functions;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicReproduction {
    static boolean firstTime = true;
    static int position = 0;
    static boolean oneHit = false;
    static boolean secHit = false;
    static List<String> songList = songs();
    public static String mediaLine = getSongList().get(position++);
    public static Media sound = new Media(new File(mediaLine).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(sound);

    public static int getPosition() {
        return position;
    }
    public static void setPosition(int position) {
        MusicReproduction.position = position;
    }

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

    public static boolean isFirstTime() {
        return firstTime;
    }
    public static void setFirstTime(boolean firstTime) {
        MusicReproduction.firstTime = firstTime;
    }

    public static void changeSongForward(){
        if (secHit) {
            position++;
            secHit = false;
        }
        System.out.println(getSongList());
        if (!(position >= getSongList().size())) {
            oneHit = true;
            System.out.println("Entro");

            asignator(position++);

            System.out.println("cancion : " + songList.get(position-1));
            System.out.println(getPosition());
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());
        }else {
            position = 0;

            asignator(position++);
            System.out.println(getPosition());


            System.out.println("cancion : " + songList.get(0));
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());
        }
    }



    public static void changeSongBackward(){
        if (oneHit) {
            position--;
            oneHit = false;
        }
        if (!(position <= 0)) {
            asignator(--position);
            secHit = true;
            System.out.println("cancion : " + songList.get(position));
            System.out.println(getPosition());
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());
        }else {
            position = songList.size();
            asignator(--position);
            System.out.println(getPosition());
            System.out.println("La que se está repr: " + mediaPlayer.getMedia().getSource());


        }
    }

    public static void reproduction(){
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }
    public static void stopReproduction(){
        mediaPlayer.stop();
//        if (mediaPlayer != null){
//            mediaPlayer.dispose();
//        }
    }
    public static void pauseReproduction(){
        mediaPlayer.pause();
    }

    public static void asignator(int positionOf) {
        if (mediaPlayer != null) {
            mediaPlayer.setAutoPlay(false);
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        setMediaLine(getSongList().get(positionOf));
        setSound(new Media(new File(getMediaLine()).toURI().toString()));
        setMediaPlayer(new MediaPlayer(getSound()));
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
        setSongList(songInFiles);
        return songInFiles;
    }

    public static void volumeChanger(Slider slider){
        if (isFirstTime()){
            slider.setValue(50);
            setFirstTime(false);
        }
        System.out.println("Volumen: " + slider.getValue());
        getMediaPlayer().setVolume(slider.getValue() / 100);

    }


}
