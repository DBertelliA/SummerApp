package com.example.summerapp.Controllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    @FXML
    public Label userLoggedMenu;

    @FXML
    public TableView<ObservableList<String>> dataClasify;

    @FXML
    public TabPane nameTabss;

    @FXML
    public AnchorPane anchorPaneContent;

    public void inicializateTable(){
        dataClasify.getColumns().clear();
        TablesFunctions.contentTypeGiver(nameTabss.getTabs().get(0).getText(),dataClasify);
        int i = nameTabss.getTabs().size();
        System.out.println(i);

    }
    public void inicializateTabs(){
        TablesFunctions.titleGiver(nameTabss);
    }

}
