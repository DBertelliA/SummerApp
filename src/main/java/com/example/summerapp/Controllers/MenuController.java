package com.example.summerapp.Controllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class MenuController {
    @FXML
    public Label userLoggedMenu;

    @FXML
    public TableView<ObservableList<String>> dataClasify;

    @FXML
    public TabPane nameTabss;

    public void inicializateTable(){
        cleanTabs();
        inicializateTabs();
        //Estructura para añadir
        int j = nameTabss.getTabs().size();
        Tab tab1 = new Tab();
        for (int i = 0; i < j; i++) {
            tab1.setContent(TablesFunctions.contentTypeGiver(nameTabss.getTabs().get(i).getText(), dataClasify));
            nameTabss.getTabs().get(i).setContent(tab1.getContent());
        }
        //
    }
    public void inicializateTabs(){
        TablesFunctions.titleGiver(nameTabss);
    }
    public void cleanTabs(){
        nameTabss.getTabs().clear();
    }

}
