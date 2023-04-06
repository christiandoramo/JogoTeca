package br.jogoteca.system.application.acessarea;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class AcessAreaTest extends Application {	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		///////////////////////////
		// substituindo tela inicial de login pela tela de testes
		root= FXMLLoader.load(getClass().getResource("TelaDeTestes.fxml"));
		/////////////////////////
		Scene scene = new Scene(root, root.getWidth(), root.getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
