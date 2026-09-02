package com.example.summerapp.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdminIntroductionController{
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //Hardcode needed ----------------------------------------------------------
    String nameAdmin;
    String passAdmin;
    String magicWordClass;

    public String getMagicWordClass() {
        return magicWordClass;
    }

    public void setMagicWordClass(String magicWordClass) {
        this.magicWordClass = magicWordClass;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }

    public void credentialSetter() {
        JOptionPane.showMessageDialog(null, "This should be only visible in the beginning of the data base");
        JOptionPane.showMessageDialog(null, "You are free to put what ever password and userName you want");
        JOptionPane.showMessageDialog(null, "I recommend to put specials characters or something under 8 words long (You aren't limited, but you can't let it empty)");
        JOptionPane.showMessageDialog(null, "If you forget about your credentials, the admin pane is fully block until you remember");
        JOptionPane.showMessageDialog(null, "THERE IS NO WAY TO CHANGE THE PASSWORD AND USER NAME, YOU KNOW THE ONLY SOLUTION IN THAT CASE");


        while (getNameAdmin() == null || getNameAdmin().isEmpty()){
            setNameAdmin(JOptionPane.showInputDialog("User"));
        }
        while (getPassAdmin() == null || getPassAdmin().isEmpty()) {
            setPassAdmin(JOptionPane.showInputDialog("Password"));
        }
        while (getMagicWordClass() == null || getMagicWordClass().isEmpty()) {
            setMagicWordClass(JOptionPane.showInputDialog("The needed word pls"));
        }

        try {
            FileReader flR2 = new FileReader("src/main/resources/AdminKey/keyKepper.json");
            JSONParser parserJ2 = new JSONParser();
            Object obj = parserJ2.parse(flR2);

            JSONObject jObject = (JSONObject) obj;
            JSONArray jArray = (JSONArray)jObject.get("keyAdmin");

            JSONObject jObj = (JSONObject) jArray.get(0);


            jObj.put("nameAdmin", encoder.encode(getNameAdmin()));
            jObj.put("passAdmin", encoder.encode(getPassAdmin()));
            jObj.put("magicWord", encoder.encode(getMagicWordClass()));

            try (FileWriter writer = new FileWriter("src/main/resources/AdminKey/keyKepper.json")) {
                writer.write(jObject.toJSONString());
            }

        } catch (IOException e) {
            System.err.println("no se pudo: " + e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] lectureCredential(){
        try{
            FileReader flR = new FileReader("src/main/resources/AdminKey/keyKepper.json");
            JSONParser parserJ = new JSONParser();
            Object stringKey = parserJ.parse(flR);

            JSONObject jsonObj = (JSONObject) stringKey;
            JSONArray array = (JSONArray)jsonObj.get("keyAdmin");

            JSONObject jsonObjectD = (JSONObject) array.get(0);

            return new String[]{(String) jsonObjectD.get("nameAdmin"), (String) jsonObjectD.get("passAdmin"), (String) jsonObjectD.get("magicWord")};
        } catch (IOException | ParseException e) {
            System.err.println(e);
        }

        return null;
    }



}
