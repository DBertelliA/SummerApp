package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;

import java.util.Scanner;


public class MenuController_All {
    M_addFunctions m_A = new M_addFunctions();
    FrameController assistentEmotions = new FrameController();
    TablesAutoCreateAndFuntions tACF = new TablesFunctions();

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    @FXML public TabPane nameTabss;

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public AnchorPane anchorPaneAddFunctions;

    @FXML public TextFlow dialogText;

    @FXML public ImageView assistent;


    //-----Para agregar Tablas-----//

    @FXML public Button buttonForNext1;

    @FXML public Button buttonForNext2;

    @FXML public Button buttonForNext3;

    @FXML public AnchorPane anchorPaneTablesAdd;

    @FXML public Pane paneNameTable;

    @FXML public Pane paneNameData;

    @FXML public Pane paneNumberData;

    @FXML public TextField titleTable;

    @FXML public TextField dataName;

    @FXML public Spinner<Integer> numberValues;

    //-----------------------------//

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

        assistent.setOnMouseClicked(event -> frameChangerMainMenu());
    }

    private void frameChangerMainMenu() {
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

    //----Funciones de agregar tabla----//

    public void addTable(){
        if (anchorPaneTablesAdd.isVisible() || !anchorPaneTablesAdd.isDisable()){
            anchorPaneTablesAdd.setVisible(false);
            anchorPaneTablesAdd.setDisable(true);
            paneNameData.setDisable(true);
            paneNameData.setVisible(false);

            paneNameTable.setDisable(true);
            paneNameTable.setVisible(false);

            paneNumberData.setDisable(true);
            paneNumberData.setVisible(false);
        }


        anchorPaneTablesAdd.setVisible(true);
        anchorPaneTablesAdd.setDisable(false);
        paneNameTable.setDisable(false);
        paneNameTable.setVisible(true);

        buttonForNext1.setOnAction(a -> {
            paneNameTable.setDisable(true);
            paneNameData.setVisible(true);
            paneNameData.setDisable(false);

            buttonForNext2.setOnAction(b -> {
                numberValues.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
                paneNameData.setDisable(true);
                paneNumberData.setVisible(true);
                paneNumberData.setDisable(false);

                buttonForNext3.setOnAction( c -> {
                    tACF.addTable(titleTable.getText(),dataName.getText(),numberValues.getValue());

                    anchorPaneTablesAdd.setVisible(false);
                    anchorPaneTablesAdd.setDisable(true);
                    paneNameData.setDisable(true);
                    paneNameData.setVisible(false);

                    paneNameTable.setDisable(true);
                    paneNameTable.setVisible(false);

                    paneNumberData.setDisable(true);
                    paneNumberData.setVisible(false);
                });
            });
        });

    }

    public void backButton(){
        addTable();
    }

}
