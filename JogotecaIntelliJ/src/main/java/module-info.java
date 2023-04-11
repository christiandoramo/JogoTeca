module com.example.jogotecaintellij {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.jogotecaintellij.view to javafx.fxml;
    exports com.example.jogotecaintellij.view;
    exports com.example.jogotecaintellij.controller;
    opens com.example.jogotecaintellij.controller to javafx.fxml;
}