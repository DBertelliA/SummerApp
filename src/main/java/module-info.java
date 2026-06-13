module com.example.summerapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.summerapp to javafx.fxml;
    exports com.example.summerapp;
}