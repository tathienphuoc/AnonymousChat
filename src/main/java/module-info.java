module com.example.anonymouschat {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    exports Controllers;
    opens Controllers to javafx.fxml;
    exports Main;
    opens Main to javafx.fxml;
}