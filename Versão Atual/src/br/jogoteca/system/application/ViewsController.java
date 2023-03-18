package br.jogoteca.system.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewsController extends Application {
	private static Scene sceneLogin, scenePrincipalAdmin, sceneCadastro, sceneCRUDJogos, sceneConsultaClientes,
			sceneConsultaPedidos, sceneTestes;
	private static Stage primaryStage;

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		primaryStage.setTitle("Jogoteca");

		FXMLLoader screenTestes = new FXMLLoader(getClass().getResource("TelaDeTestes.fxml"));
		FXMLLoader screenLogin = new FXMLLoader(getClass().getResource("Login.fxml"));
		FXMLLoader screenPrincipalAdmin = new FXMLLoader(getClass().getResource("PrincipalAdmin.fxml"));
		FXMLLoader screenCadastro = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
		FXMLLoader screenConsultaClientes = new FXMLLoader(getClass().getResource("ConsultaClientes.fxml"));
		FXMLLoader screenConsultaPedidos = new FXMLLoader(getClass().getResource("ConsultaPedidos.fxml"));
		FXMLLoader screenCRUDJogos = new FXMLLoader(getClass().getResource("CRUDJogos.fxml"));

		Parent parentTestes = screenTestes.load();
		Parent parentLogin = screenLogin.load();
		Parent parentPrincipalAdmin = screenPrincipalAdmin.load();
		Parent parentCadastro = screenCadastro.load();
		Parent parentConsultaPedidos = screenConsultaPedidos.load();
		Parent parentConsultaClientes = screenConsultaClientes.load();
		Parent parentCRUDJogos = screenCRUDJogos.load();

		sceneTestes = new Scene(parentTestes);
		sceneLogin = new Scene(parentLogin);
		sceneCadastro = new Scene(parentCadastro);
		scenePrincipalAdmin = new Scene(parentPrincipalAdmin);
		sceneConsultaPedidos = new Scene(parentConsultaPedidos);
		sceneConsultaClientes = new Scene(parentConsultaClientes);
		sceneCRUDJogos = new Scene(parentCRUDJogos);

		stage.setScene(sceneTestes);
		stage.show();

	}

	public static void changeScreen(Tela screen) {
		if (screen == Tela.LOGIN) {
			primaryStage.setScene(sceneLogin);
		} else if (screen == Tela.CADASTRO) {
			primaryStage.setScene(sceneCadastro);
		} else if (screen == Tela.CONSULTACLIENTES) {
			primaryStage.setScene(sceneConsultaClientes);
		} else if (screen == Tela.CONSULTAPEDIDOS) {
			primaryStage.setScene(sceneConsultaPedidos);
		} else if (screen == Tela.CRUDJOGOS) {
			primaryStage.setScene(sceneCRUDJogos);
		} else if (screen == Tela.PRINCIPALADMIN) {
			primaryStage.setScene(scenePrincipalAdmin);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
