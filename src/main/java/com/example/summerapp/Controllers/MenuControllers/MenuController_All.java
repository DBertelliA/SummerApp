package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;



public class MenuController_All {
    M_addFunctions m_A = new M_addFunctions();

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    @FXML public TabPane nameTabss;

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public AnchorPane anchorPaneAddFunctions;

    @FXML public TextFlow dialogText;

    @FXML public ImageView assistent;

    public void inicializate(){
        anchorPaneAddFunctions.setVisible(false);
        anchorPaneAddFunctions.setDisable(true);

        nameTabss.setDisable(false);
        nameTabss.setVisible(true);
        cleanTabs();
        inicializateTabs();
        //Estructura para añadir
        int j = nameTabss.getTabs().size();
        Tab tab1 = new Tab();
        for (int i = 0; i < j; i++) {
            tab1.setContent(TablesFunctions.contentTypeGiver(nameTabss.getTabs().get(i).getText(), dataClasify));
            nameTabss.getTabs().get(i).setContent(tab1.getContent());
        }

    }
    public void inicializateTabs(){
        TablesFunctions.titleGiver(nameTabss);
    }
    public void cleanTabs(){
        nameTabss.getTabs().clear();
    }


    public void addButton() {
        nameTabss.setDisable(true);
        nameTabss.setVisible(false);

        anchorPaneAddFunctions.setVisible(true);
        anchorPaneAddFunctions.setDisable(false);

        assistent.setVisible(true);
        Image im = new Image(("wYDqi0.jpg"));
        assistent.setImage(im);
        assistent.setManaged(true);


        m_A.initDialog(dialogText);


    }



}
