package com.example.jogotecaintellij.view;


import com.example.jogotecaintellij.controller.SessaoUsuarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.jogotecaintellij.view.ViewController.suc;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TelaDeTestes.fxml")));// ERROR
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            String css = Objects.requireNonNull(this.getClass().getResource("estilos/view.css")).toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // inicio da sessão controller - não de uma sessão de usuario;
        try {
            suc = SessaoUsuarioController.getInstance();
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
