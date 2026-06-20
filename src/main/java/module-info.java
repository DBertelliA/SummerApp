module com.example.summerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    requires spring.security.crypto;
    requires org.jspecify;


    opens com.example.summerapp to javafx.fxml;
    opens com.example.summerapp.Controllers to javafx.fxml;
    exports com.example.summerapp.WindowAndView;
    opens com.example.summerapp.WindowAndView to javafx.fxml;
    exports com.example.summerapp;
}