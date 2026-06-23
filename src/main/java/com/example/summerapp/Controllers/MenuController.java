package com.example.summerapp.Controllers;

import com.example.summerapp.Interface.Functions.TablesFunctions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MenuController {
    @FXML
    public Label userLoggedMenu;

    @FXML
    public TableView<ObservableList<String>> dataClasify;

    public void inicializateTable(){
        dataClasify.getColumns().clear();
        TablesFunctions.contentTypeGiver("tabla4",dataClasify);
    }

}
