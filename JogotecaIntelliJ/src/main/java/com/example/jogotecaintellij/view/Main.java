package com.example.jogotecaintellij.view;


import java.io.IOException;

import com.example.jogotecaintellij.controller.SessaoUsuarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import static com.example.jogotecaintellij.view.ViewController.suc;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root= FXMLLoader.load(getClass().getResource("TelaDeTestes.fxml"));// ERROR
		Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		String css = this.getClass().getResource("estilos/view.css").toExternalForm();
		scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// inicio da sessão controller - não de uma sessão de usuario;
		suc = SessaoUsuarioController.getInstance();
		launch(args);
	}
}
