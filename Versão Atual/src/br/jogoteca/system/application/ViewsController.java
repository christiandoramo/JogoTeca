package br.jogoteca.system.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewsController extends Application {
	private static Scene sceneLoginView, scenePrincipalAdminView;
	private static Stage primaryStage;

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		primaryStage.setTitle("Jogoteca");

		FXMLLoader screenLoginView = new FXMLLoader(getClass().getResource("LoginView.fxml"));
		FXMLLoader screenPrincipalAdminView = new FXMLLoader(getClass().getResource("PrincipalAdminView.fxml"));

		Parent parentLoginView = screenLoginView.load();
		Parent parentPrincipalAdminView = screenPrincipalAdminView.load();

		sceneLoginView = new Scene(parentLoginView);
		scenePrincipalAdminView = new Scene(parentPrincipalAdminView);

		stage.setScene(sceneLoginView);
		stage.show();

	}

	public static void changeScreen(String screenName) {
		if (screenName.equals("LoginView")) {
			primaryStage.setScene(sceneLoginView);
		}else if(screenName.equals("PrincipalAdminView")) {
			primaryStage.setScene(scenePrincipalAdminView);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
