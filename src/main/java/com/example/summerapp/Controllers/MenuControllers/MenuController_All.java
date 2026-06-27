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
    FrameController assistentEmotions = new FrameController();

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    @FXML public TabPane nameTabss;

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public AnchorPane anchorPaneAddFunctions;

    @FXML public TextFlow dialogText;

    @FXML public ImageView assistent;

    public void inicializate(){
        anchorPaneMainMenu.setDisable(false);
        anchorPaneMainMenu.setVisible(true);

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
        anchorPaneMainMenu.setDisable(true);
        anchorPaneMainMenu.setVisible(false);

        nameTabss.setDisable(true);
        nameTabss.setVisible(false);

        anchorPaneAddFunctions.setVisible(true);
        anchorPaneAddFunctions.setDisable(false);

        assistent.setVisible(true);
        assistent.setManaged(true);
        assistent.setPickOnBounds(true);

        m_A.initDialog(dialogText);
        Image im = new Image(("Happy-Frame.jpg"));
        assistent.setImage(im);

        assistent.setOnMouseClicked(event -> {
            if (m_A.counter > 2) {
                m_A.counter = 0;
                assistentEmotions.framesView(assistent,0);
            }
            System.out.println("contador: " + m_A.counter);
            if (m_A.counter < m_A.dialogs.length-1){
                if (m_A.counter == -1) {
                    assistentEmotions.framesView(assistent,0);
                    m_A.counter++;
                }else {
                    assistentEmotions.framesView(assistent,m_A.counter);
                    m_A.counter++;
                }
                m_A.initDialog(dialogText);
            }
        }
        );
       // assistent.setOnMouseClicked();


    }



}
