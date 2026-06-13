module com.example.summerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.summerapp to javafx.fxml;
    opens com.example.summerapp.Controllers to javafx.fxml;
    exports com.example.summerapp;
}