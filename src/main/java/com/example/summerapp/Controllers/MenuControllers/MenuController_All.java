package com.example.summerapp.Controllers.MenuControllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import com.example.summerapp.Interface.TablesAutoCreateAndFuntions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MenuController_All {
    private static ErrorReactionsFramesController err = new ErrorReactionsFramesController();
    TablesAutoCreateAndFuntions tACF = new TablesFunctions(err);

    @FXML public AnchorPane anchorPaneMainMenu;

    @FXML public TabPane nameTabss;

    @FXML public Label userLoggedMenu;

    @FXML public TableView<ObservableList<String>> dataClasify;

    //------Asistente-----//

    @FXML public ImageView assistent;

    @FXML public TextFlow dialogText;

    @FXML public TextFlow dialogText2;

    //-----Para agregar Tablas-----//

    @FXML public Pane paneNameTable;

    @FXML public Pane paneNumberData;

    @FXML public Button buttonForNext1;

    @FXML public Button buttonForNext2;

    @FXML public Button buttonForBack;

    @FXML public TextField titleTable;

    @FXML public Spinner<Integer> numberValues;

    //-----------------------------//

    //-------Selector-------//

    @FXML public AnchorPane anchorPaneAddFunctions; //para tabala y datos agregar
    @FXML public Pane paneSelector;

    @FXML public ToolBar selectorOptions;

    //--------Agregar datos--------//

    @FXML public AnchorPane anchorPaneTablesAdd;

    @FXML public AnchorPane anchorPaneAddData;

    @FXML public ComboBox<String> comboxTable;

    @FXML public Label labelIndicate;

    //--------Editar y eliminar datos----------//

    @FXML public AnchorPane anchorPaneForEditValues;

    @FXML public Pane paneForEditFields;

    @FXML public Pane paneForDeleteFields;

    @FXML public ComboBox<String> comboBoxForEdit;

    @FXML public TableView<ObservableList<String>> singleTableEdit;

    @FXML public Button buttonForEditData;

    @FXML public Button buttonForDeleteData;

    @FXML public CheckBox checkEdit;

    @FXML public CheckBox checkDelete;

    @FXML public Label labelText;

    //---------------Buscar datos------------------//

    @FXML public AnchorPane anchorPaneForSearchData;

    @FXML public Pane paneForSearchFields;

    @FXML public ComboBox<String> tablesForSearch;

    @FXML public TableView<ObservableList<String>> tableShowDataSelected;

    @FXML public Button buttonForSearch;

    //---------------Eliminar tablas-----------------//

    @FXML public AnchorPane anchorPaneForDeleteTable;

    @FXML public ComboBox<String> comboxOfTablesForDelete;

    @FXML public Button buttonForDeleteTable;

    @FXML public Label labelSelectedTable;

    //------Metodos necesario------//

    private List<String> valuesForMe;

    private List<String> nameOfData;

    private int oneOrC = 0;

    private void visualizerMethod(int place){
        //True: para ver y habilitar
        //False: para ocultar y deshabilitar
        turnerOff();
        switch (place){
            case 1 -> {
                //inicio
                anchorPaneMainMenu.setVisible(true);
                anchorPaneMainMenu.setDisable(false);
            }
            case 2 -> {
                //Agregar tablas y datos
                anchorPaneAddFunctions.setVisible(true);
                anchorPaneAddFunctions.setDisable(false);
                paneSelector.setVisible(true);
                paneSelector.setDisable(false);
            }
            case 3 ->{
                //Editar y eliminar datos
                anchorPaneTablesAdd.setVisible(true);
                anchorPaneTablesAdd.setDisable(false);
                anchorPaneForEditValues.setVisible(true);
                anchorPaneForEditValues.setDisable(false);

                paneForEditFields.setVisible(true);
                paneForEditFields.setDisable(false);
                //-----//
                paneForDeleteFields.setVisible(true);
                paneForDeleteFields.setDisable(false);
            }
            case 4 -> {
                //Buscar datos
                anchorPaneForSearchData.setVisible(true);
                anchorPaneForSearchData.setDisable(false);

                paneForSearchFields.setVisible(true);
                paneForSearchFields.setDisable(false);
            }
            case 5 -> {
                //Eliminar tablas
                anchorPaneForDeleteTable.setVisible(true);
                anchorPaneForDeleteTable.setDisable(false);
            }
            default -> System.exit(0);
        }
    }

    private void turnerOff(){
        //inicio
        anchorPaneMainMenu.setVisible(false);
        anchorPaneMainMenu.setDisable(true);

        //Agregar tablas y datos
        anchorPaneAddFunctions.setVisible(false);
        anchorPaneAddFunctions.setDisable(true);
        paneSelector.setVisible(false);
        paneSelector.setDisable(true);
        //-----//
        paneNameTable.setVisible(false);
        paneNameTable.setDisable(true);
        //-----//
        paneNumberData.setVisible(false);
        paneNumberData.setDisable(true);
        //-----//

        //Editar y eliminar datos
        anchorPaneTablesAdd.setVisible(false);
        anchorPaneTablesAdd.setDisable(true);

        anchorPaneForEditValues.setVisible(false);
        anchorPaneForEditValues.setDisable(true);
        //-----//
        paneForEditFields.setVisible(false);
        paneForEditFields.setDisable(true);
        //-----//
        paneForDeleteFields.setVisible(false);
        paneForDeleteFields.setDisable(true);

        //Buscar datos
        anchorPaneForSearchData.setVisible(false);
        anchorPaneForSearchData.setDisable(true);
        //-----//
        paneForSearchFields.setVisible(false);
        paneForSearchFields.setDisable(true);


        //Eliminar tablas
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);

        selectorOptions.setDisable(false);
        selectorOptions.setVisible(true);

    }


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

    public void backInit(boolean switch2){
        //true solo por ahora
        selectorOptions.setDisable(!switch2);
        paneSelector.setDisable(!switch2);

        anchorPaneAddData.setDisable(switch2);
        anchorPaneAddData.setVisible(!switch2);

        comboxTable.setVisible(!switch2);
        comboxTable.setDisable(switch2);

        anchorPaneTablesAdd.setVisible(false);
        anchorPaneTablesAdd.setDisable(true);

        paneSelector.setDisable(false);
        paneSelector.setVisible(true);
        selectorOptions.setDisable(false);

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
        //paneNameData.setDisable(true);
        //paneNameData.setVisible(false);

        paneNameTable.setDisable(true);
        paneNameTable.setVisible(false);

        paneNumberData.setDisable(true);
        paneNumberData.setVisible(false);
    }

    //----------------------------------------------//
    //----------------
    //--------
    boolean inited = false;
    public void inicializate(){
        visualizerMethod(1);
        putPointsAndVisible(true);
        visibilityAssistent(false);
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

        if (!inited) {
            FrameController.initAssist(dialogText, assistent);
            inited = true;
        }
        putPointsAndVisible(true);
        visibilityAssistent(true);
        visualizerMethod(2);
        if (oneOrC == 0) {
            TablesFunctions.dialogGenerator(assistent, 1, dialogText, "Has accedido a las funciones para añadir tablas o datos");
        }else if (oneOrC == 1){
            TablesFunctions.dialogGenerator(assistent, 1, dialogText, "Se ha agregado la tabla");
            oneOrC = 0;
        }
    }

    //----Funciones de agregar tabla----//

    public void addTable(){
        numberValues.setDisable(false);
        buttonForNext2.setDisable(true);
        buttonForNext1.setDisable(false);
        AtomicInteger fish = new AtomicInteger();
        fish.set(0);
        anchorPaneForDeleteTable.setVisible(false);
        anchorPaneForDeleteTable.setDisable(true);
        paneSelector.setDisable(true);
        paneSelector.setVisible(false);
        selectorOptions.setDisable(true);
        numberValues.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10));

        if (anchorPaneTablesAdd.isVisible() || !anchorPaneTablesAdd.isDisable()){
            DisablerOrAForAddT();
        }
        anchorPaneTablesAdd.setVisible(true);
        anchorPaneTablesAdd.setDisable(false);
        paneNameTable.setDisable(false);
        paneNameTable.setVisible(true);
        paneNumberData.setDisable(false);
        paneNumberData.setVisible(true);

        buttonForNext1.setOnAction( e -> {
            numberValues.setDisable(true);
            if (fish.get() <= numberValues.getValue()) {
                if (fish.get() >= 1 && fish.get() < numberValues.getValue()){
                    anchorPaneTablesAdd.getChildren().forEach(i -> {
                       if (i instanceof Pane){
                           Pane pane = (Pane) i;
                           pane.setDisable(true);
                       }
                   });
                }
                if (!(numberValues.getValue() - fish.get() <= 0)) {
                    TablesFunctions.panesAdder(anchorPaneTablesAdd, numberValues.getValue(), fish.get());
                }
                fish.getAndIncrement();
                if (fish.get() == numberValues.getValue()+1){
                    buttonForNext2.setDisable(false);
                    for (Node n : anchorPaneTablesAdd.getChildren()){
                        if (n instanceof Pane pa) {
                            pa.setDisable(true);
                            buttonForNext1.setDisable(true);
                        }
                    }
                }
            }else{
                System.out.println("no");
            }

        });
        buttonForNext2.setOnAction(e -> {
            List<String> listDataToForm = new ArrayList<>();
            for (Node p : anchorPaneTablesAdd.getChildren()){
                if (p instanceof Pane pane) {
                    for (Node childText : pane.getChildren()) {
                        if (childText instanceof TextField tf) {
                            listDataToForm.add(tf.getText());
                        }
                    }
                }
            }
            List<String> listOfTypes = new ArrayList<>();
            for (Node p : anchorPaneTablesAdd.getChildren()){
                if (p instanceof Pane pane){
                    for (Node panel : pane.getChildren()) {
                        if (panel instanceof ComboBox<?> combo) {
                            listOfTypes.add((String) combo.getValue());
                        }
                    }
                }
            }
            if(!tACF.addTable(listDataToForm,listOfTypes, titleTable.getText() ,dialogText ,assistent)){
                FrameController.framesView(assistent, 3);
                Font font = Font.font(20);
                Text textAssign = new Text("Error(1)");

                textAssign.setFont(font);
                textAssign.setFill(Color.WHITE);

                textAssign.setTextAlignment(TextAlignment.LEFT);
                dialogText.getChildren().clear();
                dialogText.getChildren().add(textAssign);

                AtomicInteger i = new AtomicInteger();
                assistent.setOnMouseClicked( c -> {
                    err.errorWarning(dialogText, assistent, i.get());
                    i.getAndIncrement();
                    if (i.get() > 5){
                        anchorPaneTablesAdd.getChildren().removeIf( p -> p instanceof Pane );
                        titleTable.clear();
                        oneOrC = 0;
                        addButton();
                    }
                });

                }else {
                    anchorPaneTablesAdd.getChildren().removeIf(p -> p instanceof Pane);
                    titleTable.clear();
                    oneOrC = 1;
                    addButton();
                }

        });
        buttonForBack.setOnAction( e -> {
            anchorPaneTablesAdd.setVisible(false);
            anchorPaneTablesAdd.setDisable(true);

            paneSelector.setDisable(false);
            paneSelector.setVisible(true);
            selectorOptions.setDisable(false);
            anchorPaneTablesAdd.getChildren().removeIf( p -> p instanceof Pane );
            titleTable.clear();
            addButton();
        });

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
        paneSelector.setVisible(false);
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
        boolean che = tACF.insertData(comboxTable.getValue(),lStr, dialogText ,assistent);
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
    if (!inited) {
        FrameController.initAssist(dialogText, assistent);
        inited = true;
    }
    singleTableEdit.getSelectionModel().setCellSelectionEnabled(true);
    putPointsAndVisible(false);
    visibilityAssistent(true);
    visualizerMethod(3);


    TablesFunctions.fillerBox(comboBoxForEdit);

    TablesFunctions.dialogGenerator(assistent,1,dialogText,"Has accedido a editar o eliminar datos");


    comboBoxForEdit.setOnAction( e -> {
        TablesFunctions.contentTypeGiver(comboBoxForEdit.getValue(), singleTableEdit);
        paneForEditFields.getChildren().removeIf(i -> i instanceof TextField);
        TablesFunctions.autoGenerateTextFields(comboBoxForEdit.getValue(),null, paneForEditFields);
    });

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
        tACF.deleteData(comboBoxForEdit.getValue(), valuesForMe, nameOfData, dialogText ,assistent);
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
            tACF.updateData(comboBoxForEdit.getValue(),stringsList, valuesForMe, dialogText ,assistent);
            TablesFunctions.fillerBox(comboBoxForEdit);
        });

    singleTableEdit.setOnMouseClicked(e -> {
            //Esto devuelve una lista de objetos de esa fila, siendo primero necesitamos setear, que se debe de obtener del modelo la fila seccionada
            //Lo que devuelve un valor
        try {
            TablePosition<ObservableList<String>, String> position = singleTableEdit.getSelectionModel().getSelectedCells().get(0);
            //Luego, usamos ese valor para llamar a los items de la posicion seleccionada
            valuesForMe = singleTableEdit.getItems().get(position.getRow());
            nameOfData = TablesFunctions.giverName(comboBoxForEdit.getValue(), dialogText, assistent);

            StringBuilder sb = new StringBuilder();
            System.out.println(valuesForMe);
            int i = 0;

            for (Node p : paneForEditFields.getChildren()) {
                if (p instanceof TextField) {
                    TextField tf = (TextField) p;
                    tf.setText(valuesForMe.get(i));
                    sb.append(nameOfData.get(i)).append(" : ").append(valuesForMe.get(i)).append("\n");
                    labelText.setText(sb.toString());
                    i++;

                }
            }
            buttonForEditData.setDisable(false);
            System.out.println(sb.toString());
        }catch (IndexOutOfBoundsException err){
            FrameController.framesView(assistent, 3);
            Font font = Font.font(20);
            Text textAssign = new Text("No hay data en esa tabla amigo... que haces?");

            textAssign.setFont(font);
            textAssign.setFill(Color.WHITE);

            textAssign.setTextAlignment(TextAlignment.LEFT);
            dialogText.getChildren().clear();
            dialogText.getChildren().add(textAssign);
        }
        });


    }

    //-----------------------------------------------//
    //-----------------------
    //----------


    //-------------Buscar datos de tablas------------//

    public void searchPane(){
        if (!inited) {
            FrameController.initAssist(dialogText, assistent);
            inited = true;
        }
        buttonForSearch.setDisable(true);
        putPointsAndVisible(true);
        visibilityAssistent(true);
        visualizerMethod(4);

        TablesFunctions.fillerBox(tablesForSearch);

        TablesFunctions.dialogGenerator(assistent,1,dialogText, "Estas accediendo a la busqueda de tablas. Si pones un dato en esa tabla y coincide, te mostrar el dato, si no pones nada te los mostrará todo.");

        tablesForSearch.setOnAction( e -> {
            paneForSearchFields.getChildren().removeIf( i -> i instanceof TextField);
            tableShowDataSelected.getColumns().clear();
            tableShowDataSelected.getItems().clear();
            buttonForSearch.setDisable(false);
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
            tACF.dataSearch(tablesForSearch.getValue(),listData,tableShowDataSelected, dialogText ,assistent);
        });

    }

    //------Eliminar tablas-------//

    public void deleteTables(){
        if (!inited) {
            FrameController.initAssist(dialogText, assistent);
            inited = true;
        }

        buttonForDeleteTable.setDisable(true);
        labelSelectedTable.setText("...");

        putPointsAndVisible(true);
        visibilityAssistent(true);
        visualizerMethod(5);

        TablesFunctions.fillerBox(comboxOfTablesForDelete);

        TablesFunctions.dialogGenerator(assistent,1,dialogText,"Estas accediendo a la funcion de eliminar tablas");

                comboxOfTablesForDelete.setOnAction(event -> {
            labelSelectedTable.setText("you selected :" + comboxOfTablesForDelete.getValue());
            buttonForDeleteTable.setDisable(false);
        });

        buttonForDeleteTable.setOnAction( e -> {
            if (tACF.deleteTables(comboxOfTablesForDelete.getValue(), dialogText ,assistent)) {
                TablesFunctions.fillerBox(comboxOfTablesForDelete);
                labelSelectedTable.setText("Deleted");
                buttonForDeleteTable.setDisable(true);
            }else {
                labelSelectedTable.setText("For some reason, it's still there");
            }
        } );

    }

}
