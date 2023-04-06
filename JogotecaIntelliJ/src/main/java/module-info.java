module com.example.jogotecaintellij {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jogotecaintellij.view to javafx.fxml;
    exports com.example.jogotecaintellij.view;
}