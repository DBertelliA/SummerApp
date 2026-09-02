package com.example.summerapp.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class dada {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsoP = new JSONParser();
        FileReader readerFl = new FileReader("src/main/resources/Record/memory(Beta).json");
        JSONObject jsonO = (JSONObject) jsoP.parse(readerFl);
        JSONArray jArray = (JSONArray) jsonO.get("DataBaseStatus");

        JSONObject json12 = (JSONObject) jArray.get(0);
        json12.put("started",true);

        try (FileWriter fr = new FileWriter("src/main/resources/Record/memory(Beta).json")){
            fr.write(jsonO.toJSONString());
        }
        System.out.println(json12.get("started"));
    }
}