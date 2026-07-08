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
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MenuController_All {
    M_addFunctions m_A = new M_addFunctions();
    FrameController assistentEmotions = new FrameController();
    TablesAutoCreateAndFuntions tACF = new TablesFunctions();

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    @FXML public TabPane nameTabss;

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public AnchorPane anchorPaneAddFunctions;

    //------Asistente-----//

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

    //-------Selector-------//

    @FXML public Pane paneSelector;

    @FXML public ToolBar selectorOptions;

    //--------Agregar datos--------//

    @FXML public ComboBox<String> comboxTable;

    @FXML public AnchorPane anchorPaneAddData;

    @FXML public Label labelIndicate;

    //--------Editar datos----------//

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

    private List<String> valuesForMe;

    private List<String> nameOfData;

    public void inicializate(){
        anchorPaneForEditValues.setDisable(true);
        anchorPaneForEditValues.setVisible(false);

        dialogText.setDisable(true);
        dialogText.setVisible(false);

        assistent.setVisible(false);
        assistent.setDisable(true);

        TablesFunctions.fillerBox(comboxTable);
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
        anchorPaneForEditValues.setDisable(true);
        anchorPaneForEditValues.setVisible(false);

        dialogText.setDisable(false);
        dialogText.setVisible(true);

        anchorPaneMainMenu.setDisable(true);
        anchorPaneMainMenu.setVisible(false);

        nameTabss.setDisable(true);
        nameTabss.setVisible(false);

        anchorPaneAddFunctions.setVisible(true);
        anchorPaneAddFunctions.setDisable(false);

        assistent.setVisible(true);
        assistent.setDisable(false);
        assistent.setManaged(true);
        assistent.setPickOnBounds(true);

        m_A.initDialog(dialogText);
        Image im = new Image((Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/Happy-new.jpg"))));
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

    public void backButton(){
        addTable();
    }


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
            selectorOptions.setDisable(false);
            paneSelector.setDisable(false);
            anchorPaneAddData.setDisable(true);
            anchorPaneAddData.setVisible(false);
            comboxTable.setVisible(false);
            comboxTable.setDisable(true);
            labelIndicate.setText("...");
        }
    }
    public void backButtonInAddData(){
        selectorOptions.setDisable(false);
        paneSelector.setDisable(false);
        anchorPaneAddData.setDisable(true);
        anchorPaneAddData.setVisible(false);
        comboxTable.setVisible(false);
        comboxTable.setDisable(true);
    }

    //----------Funciones para editar y eliminar------------//

    public void editPane(){
        singleTableEdit.getSelectionModel().setCellSelectionEnabled(true);

        dialogText.setVisible(true);
        dialogText.setDisable(false);
        assistent.setVisible(true);
        assistent.setDisable(false);

        anchorPaneMainMenu.setDisable(true);
        anchorPaneMainMenu.setVisible(false);
        anchorPaneAddFunctions.setVisible(false);
        anchorPaneAddFunctions.setDisable(true);

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


//        comboBoxForEdit.setDisable(false);
//        comboBoxForEdit.setVisible(true);
//
//
//        sliderX.setDisable(false);
//        sliderX.setVisible(true);
//
//
//        sliderY.setDisable(false);
//        sliderY.setVisible(true);
//
//        singleTableEdit.setDisable(false);
//        singleTableEdit.setVisible(true);
//
//        paneForEditFields.setDisable(false);
//        paneForEditFields.setVisible(true);

        anchorPaneForEditValues.setDisable(false);
        anchorPaneForEditValues.setVisible(true);

    }



}
