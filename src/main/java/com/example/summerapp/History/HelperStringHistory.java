package com.example.summerapp.History;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class HelperStringHistory {
    public static void historyMaker(String message){
        try (FileWriter flW = new FileWriter("src/main/resources/Record/WhatHappendInDB(Beta).txt", true)){

            LocalDate lD = LocalDate.now();
            LocalTime lT = LocalTime.now();

            StringBuilder sb = new StringBuilder();
            sb.append("[").append(lD.getDayOfMonth()).append("/").append(lD.getMonthValue()).append("/").append(lD.getYear()).append("]")
                            .append(" - ").append(lT.getHour()).append(":").append(lT.getMinute()).append(":").append(lT.getSecond())
                            .append(" - ").append(message).append(". \n");
            flW.write(sb.toString());
        }catch (IOException e){
            System.err.println("Algo ha ocurrido que no se ha podido escribir en el historial");
        }
    }
}
