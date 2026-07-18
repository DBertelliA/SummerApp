package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MenuController_All {
    TablesAutoCreateAndFuntions tACF = new TablesFunctions();
    M_addFunctions m_a = new M_addFunctions();

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    @FXML public TabPane nameTabss;

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public AnchorPane anchorPaneAddFunctions;

    //------Asistente-----//

    @FXML public TextFlow dialogText;

    @FXML public ImageView assistent;

    @FXML public TextFlow dialogText2;

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

    //-------Selector-------//

    @FXML public Pane paneSelector;

    @FXML public ToolBar selectorOptions;

    //--------Agregar datos--------//

    @FXML public ComboBox<String> comboxTable;

    @FXML public AnchorPane anchorPaneAddData;

    @FXML public Label labelIndicate;

    //--------Editar y eliminar datos----------//

    @FXML public AnchorPane anchorPaneForEditValues;

    @FXML public ComboBox<String> comboBoxForEdit;

    @FXML public TableView<ObservableList<String>> singleTableEdit;

    @FXML public Pane paneForEditFields;

    @FXML public Pane paneForDeleteFields;

    @FXML public Button buttonForEditData;

    @FXML public Button buttonForDeleteData;

    @FXML public CheckBox checkEdit;

    @FXML public CheckBox checkDelete;

    @FXML public Label labelText;

    //---------------Buscar datos------------------//

    @FXML public AnchorPane anchorPaneForSearchData;

    @FXML public ComboBox<String> tablesForSearch;

    @FXML public TableView<ObservableList<String>> tableShowDataSelected;

    @FXML public Button buttonForSearch;

    @FXML public Pane paneForSearchFields;

    //---------------Eliminar tablas-----------------//

    @FXML public AnchorPane anchorPaneForDeleteTable;

    @FXML public Button buttonForDeleteTable;

    @FXML public ComboBox<String> comboxOfTablesForDelete;

    @FXML public Label labelSelectedTable;

    //------Metodos necesario------//

    private List<String> valuesForMe;

    private List<String> nameOfData;

    public void inicializateTabs(){TablesFunctions.titleGiver(nameTabss);}
    public void cleanTabs(){
        nameTabss.getTabs().clear();
    }

    public void visibilityAssistent(boolean switchO){
        //true: visible y funcional
        //false: !true

        dialogText.setVisible(switchO);
        dialogText.setDisable(!switchO);

        dialogText2.setVisible(switchO);
        dialogText2.setDisable(!switchO);

        assistent.setVisible(switchO);
        assistent.setDisable(!switchO);
    }

    public void visibilityPanesInit(boolean switch1){
        //true: visible y funcional
        //false: !true

        anchorPaneMainMenu.setVisible(switch1);
        anchorPaneMainMenu.setDisable(!switch1);

        nameTabss.setVisible(switch1);
        nameTabss.setDisable(!switch1);

        //Contrario

        anchorPaneAddFunctions.setVisible(!switch1);
        anchorPaneAddFunctions.setDisable(switch1);

        anchorPaneForEditValues.setVisible(!switch1);
        anchorPaneForEditValues.setDisable(switch1);
    }

    public void backInit(boolean switch2){
        //true solo por ahora
        selectorOptions.setDisable(!switch2);
        paneSelector.setDisable(!switch2);

        anchorPaneAddData.setDisable(switch2);
        anchorPaneAddData.setVisible(!switch2);

        comboxTable.setVisible(!switch2);
        comboxTable.setDisable(switch2);
    }

    private void putPointsAndVisible(boolean switch3) {
        //True: apagar
        //False: encender
        labelText.setDisable(switch3);
        labelText.setVisible(!switch3);
        labelText.setText("...");
    }

    private void DisablerOrAForAddT() {
        anchorPaneTablesAdd.setVisible(false);
        anchorPaneTablesAdd.setDisable(true);
        paneNameData.setDisable(true);
        paneNameData.setVisible(false);

        paneNameTable.setDisable(true);
        paneNameTable.setVisible(false);

        paneNumberData.setDisable(true);
        paneNumberData.setVisible(false);
    }

    //----------------------------------------------//
    //----------------
    //--------

    public void inicializate(){
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);
        putPointsAndVisible(true);
        FrameController.initAssist(dialogText, assistent);
        visibilityAssistent(false);
        visibilityPanesInit(true);

        anchorPaneForSearchData.setVisible(false);
        anchorPaneForSearchData.setDisable(true);

        TablesFunctions.fillerBox(comboxTable);
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

    public void addButton() {
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);
        putPointsAndVisible(true);
        visibilityAssistent(true);
        visibilityPanesInit(false);

        anchorPaneForEditValues.setDisable(true);
        anchorPaneForEditValues.setVisible(false);

        anchorPaneForSearchData.setVisible(false);
        anchorPaneForSearchData.setDisable(true);

    }
    //----Funciones de agregar tabla----//

    public void addTable(){
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);
        paneSelector.setDisable(true);
        selectorOptions.setDisable(true);
        if (anchorPaneTablesAdd.isVisible() || !anchorPaneTablesAdd.isDisable()){
            DisablerOrAForAddT();
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
                    DisablerOrAForAddT();
                    paneSelector.setDisable(false);
                    selectorOptions.setDisable(false);
                });
            });
        });
    }

    public void backButtonForTable(){
        anchorPaneTablesAdd.setVisible(false);
        anchorPaneTablesAdd.setDisable(true);
        paneSelector.setDisable(false);
        selectorOptions.setDisable(false);
    }


    public void backButton(){
        addTable();
    }

    //----------------------------------------------//
    //----------------------
    //-----------


    //-------------Funciones de añadir datos a tablas o añadir tablas-------------//
    public void addData(){

        paneSelector.setDisable(true);
        selectorOptions.setDisable(true);

        anchorPaneAddData.getChildren().removeIf( e -> e instanceof TextField);
        //o puedo reusar el metodo de la autocreacion de campos
        //para que me devuelva una lista que recoja esos campos y que remueva toda esa lista que le pasé
        //lo primero es lo mas sencillo y muy util para todo
        anchorPaneAddData.setDisable(false);
        anchorPaneAddData.setVisible(true);
        comboxTable.setVisible(true);
        comboxTable.setDisable(false);
        comboxTable.setOnAction(i -> {
            anchorPaneAddData.getChildren().removeIf(e -> e instanceof TextField);
            updateForAdd();
        });
    }

    public void updateForAdd(){
        labelIndicate.setText("Estas usando la tabla: " + comboxTable.getValue());
        TablesFunctions.autoGenerateTextFields(comboxTable.getValue(),anchorPaneAddData,null);
    }

    public void buttonForAddData(){
        List<String> lStr = new ArrayList<>();
        for (Node n : anchorPaneAddData.getChildren()){
            if (n instanceof TextField) {
                TextField tf = (TextField) n;
                lStr.add(tf.getText());
            }
        }
        boolean che = tACF.insertData(comboxTable.getValue(),lStr);
        if (che) {
            TablesFunctions.autoGenerateTextFields(comboxTable.getValue(), anchorPaneAddData, null);
            backInit(true);
            labelIndicate.setText("...");
        }
    }

    public void backButtonInAddData(){
        backInit(true);
        labelIndicate.setText("...");
    }
    //------------------------------------------------------//
    //---------------------
    //-----------

    //----------Funciones para editar y eliminar------------//

    public void editPane(){
    putPointsAndVisible(false);
    visibilityAssistent(true);
    anchorPaneForEditValues.setDisable(false);
    anchorPaneForEditValues.setVisible(true);

    anchorPaneForSearchData.setVisible(false);
    anchorPaneForSearchData.setDisable(true);

    singleTableEdit.getSelectionModel().setCellSelectionEnabled(true);

    anchorPaneMainMenu.setDisable(true);
    anchorPaneMainMenu.setVisible(false);
    anchorPaneAddFunctions.setVisible(false);
    anchorPaneAddFunctions.setDisable(true);

    anchorPaneForDeleteTable.setVisible(false);
    anchorPaneForDeleteTable.setDisable(true);

       TablesFunctions.fillerBox(comboBoxForEdit);
        comboBoxForEdit.setOnAction( e -> {
            TablesFunctions.contentTypeGiver(comboBoxForEdit.getValue(), singleTableEdit);
            paneForEditFields.getChildren().removeIf(i -> i instanceof TextField);
            TablesFunctions.autoGenerateTextFields(comboBoxForEdit.getValue(),null, paneForEditFields);

            for (Node n : paneForEditFields.getChildren()){
                    if (n instanceof TextField){
                    buttonForEditData.setDisable(false);
                        }
                    }
            }
        );

        checkEdit.setSelected(true);
        checkDelete.setSelected(false);
        paneForDeleteFields.setDisable(true);
        paneForEditFields.setDisable(false);
        buttonForEditData.setDisable(true);


        checkEdit.setOnAction( e -> {
            if(checkDelete.isPressed()){checkDelete.setSelected(false);}
            checkDelete.setSelected(false);
            paneForDeleteFields.setDisable(true);
            paneForEditFields.setDisable(false);
        });

        checkDelete.setOnAction( e -> {
            if (checkEdit.isPressed()){checkEdit.setSelected(false);}
            checkEdit.setSelected(false);
            paneForEditFields.setDisable(true);
            paneForDeleteFields.setDisable(false);
        });

        buttonForDeleteData.setOnAction( e -> {
            tACF.deleteData(comboBoxForEdit.getValue(), valuesForMe, nameOfData);
            TablesFunctions.fillerBox(comboBoxForEdit);
        });

        buttonForEditData.setOnAction(e -> {
            List<String> stringsList = new ArrayList<>();
            for (Node i : paneForEditFields.getChildren()){
                if (i instanceof TextField){
                    TextField tf = (TextField) i;
                    stringsList.add(tf.getText());
                }
            }
            tACF.updateData(comboBoxForEdit.getValue(),stringsList, valuesForMe);
            TablesFunctions.fillerBox(comboBoxForEdit);
        });

        singleTableEdit.setOnMouseClicked(e -> {
            //Esto devuelve una lista de objetos de esa fila, siendo primero necesitamos setear, que se debe de obtener del modelo la fila seccionada
            //Lo que devuelve un valor
            TablePosition<ObservableList<String>,String> position = singleTableEdit.getSelectionModel().getSelectedCells().get(0);

            //Luego, usamos ese valor para llamar a los items de la posicion seleccionada
            valuesForMe = singleTableEdit.getItems().get(position.getRow());
            nameOfData = TablesFunctions.giverName(comboBoxForEdit.getValue());

            StringBuilder sb = new StringBuilder();
            System.out.println(valuesForMe);
            int i = 0;

            for (Node p : paneForEditFields.getChildren()) {
                if (p instanceof TextField){
                    TextField tf = (TextField) p;
                    tf.setText(valuesForMe.get(i));
                    sb.append(nameOfData.get(i)).append(" : ").append(valuesForMe.get(i)).append("\n");
                    labelText.setText(sb.toString());
                    i++;

                }
            }
            System.out.println(sb.toString());

        });


    }

    //-----------------------------------------------//
    //-----------------------
    //----------


    //-------------Buscar datos de tablas------------//

    public void searchPane(){
        putPointsAndVisible(true);
        visibilityAssistent(true);
        visibilityPanesInit(true);
        anchorPaneMainMenu.setDisable(true);
        anchorPaneMainMenu.setVisible(false);
        anchorPaneForSearchData.setDisable(false);
        anchorPaneForSearchData.setVisible(true);
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);

        TablesFunctions.fillerBox(tablesForSearch);

        tablesForSearch.setOnAction( e -> {
            paneForSearchFields.getChildren().removeIf( i -> i instanceof TextField);
            tableShowDataSelected.getColumns().clear();
            tableShowDataSelected.getItems().clear();
            TablesFunctions.autoGenerateTextFields(tablesForSearch.getValue(),null,paneForSearchFields);
        });

        buttonForSearch.setOnAction( i -> {
            List<String> listData = new ArrayList<>();
            for (Node e : paneForSearchFields.getChildren()){
             if (e instanceof TextField){
                 TextField tf = (TextField) e;
                     listData.add(tf.getText());
                }
            }
            tACF.dataSearch(tablesForSearch.getValue(),listData,tableShowDataSelected);
        });

    }

    //------Eliminar tablas-------//

    public void deleteTables(){
        buttonForDeleteTable.setDisable(true);
        labelSelectedTable.setText("...");

        putPointsAndVisible(true);
        visibilityAssistent(true);
        visibilityPanesInit(true);

        anchorPaneMainMenu.setDisable(true);
        anchorPaneMainMenu.setVisible(false);

        anchorPaneForDeleteTable.setVisible(true);
        anchorPaneForDeleteTable.setDisable(false);

        anchorPaneForSearchData.setVisible(false);
        anchorPaneForSearchData.setDisable(true);

        TablesFunctions.fillerBox(comboxOfTablesForDelete);

        comboxOfTablesForDelete.setOnAction(event -> {
            labelSelectedTable.setText("you selected :" + comboxOfTablesForDelete.getValue());
            buttonForDeleteTable.setDisable(false);
        });

        buttonForDeleteTable.setOnAction( e -> {
            if (tACF.deleteTables(comboxOfTablesForDelete.getValue())) {
                TablesFunctions.fillerBox(comboxOfTablesForDelete);
                labelSelectedTable.setText("Deleted");
                buttonForDeleteTable.setDisable(true);
            }else {
                labelSelectedTable.setText("For some reason, it's still there");
            }
        } );

    }

}
