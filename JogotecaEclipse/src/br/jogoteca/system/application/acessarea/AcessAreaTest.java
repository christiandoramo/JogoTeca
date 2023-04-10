package br.jogoteca.system.application.acessarea;


import java.io.IOException;

import br.jogoteca.system.controllers.UserController;
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
        String css = this.getClass().getResource("view.css").toExternalForm();
        scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		UserController uc = UserController.getInstance();
		try {
		System.out.println(uc.searchUserByLogin2("login").getNome());
		System.out.println(uc.searchUserByLogin2("user1").getNome());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		launch(args);
	}
}
