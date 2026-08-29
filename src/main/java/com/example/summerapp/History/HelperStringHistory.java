package com.example.summerapp.History;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class HelperStringHistory {
    public static void historyMaker(String message){
        try (FileWriter flW = new FileWriter("src/main/resources/Record/DataBaseHistory.txt", true)){

            LocalDate lD = LocalDate.now();
            LocalTime lT = LocalTime.now();

            StringBuilder sb = new StringBuilder();
            sb.append("[").append(lD.getDayOfMonth()).append("/").append(lD.getMonthValue()).append("/").append(lD.getYear()).append("]")
                            .append("-");

            if (lT.getHour() < 10) {sb.append(":0").append(lT.getHour());}
            else {sb.append(lT.getHour());}

            if (lT.getMinute() < 10) {sb.append(":0").append(lT.getMinute());}
            else {sb.append(":").append(lT.getMinute());}

            if (lT.getSecond() < 10) {sb.append(":0").append(lT.getSecond());}
            else {sb.append(":").append(lT.getSecond());}

            sb.append("-").append(message).append(". \n");

            flW.write(sb.toString());
        }catch (IOException e){
            System.err.println("Algo ha ocurrido que no se ha podido escribir en el historial");
        }
    }
}
