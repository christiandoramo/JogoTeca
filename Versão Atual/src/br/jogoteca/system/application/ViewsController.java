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
		
		UserController userController = new UserController();

	    User user1 = new User(1, "João Silva", "Rua A, 123", "(81)91234-5678", "joao@gmail.com", "joao.silva", "123456");;
	    userController.addUser(user1);

	    User user2 = new User(2, "Maria Souza", "Rua B, 456", "(31)92345-6789", "maria@gmail.com", "maria.souza", "654321");
	    userController.addUser(user2);

	    User user3 = new User(3, "José Santos", "Rua C, 789", "(47)93456-7890", "jose@gmail.com", "jose.santos", "senha123");
	    userController.addUser(user3);

	    User user4 = new User(4, "Ana Paula", "Rua D, 321", "(11)94567-8901", "ana@gmail.com", "ana.paula", "senha456");
	    userController.addUser(user4);

	    User user5 = new User(5, "Pedro Oliveira", "Rua E, 654", "(22)95678-9012", "pedro@gmail.com", "pedro.oliveira", "senha789");
	    userController.addUser(user5);

	    User user6 = new User(6, "Carla Silva", "Rua F, 987", "(57)96789-0123", "carla@gmail.com", "carla.silva", "senhaabc");
	    userController.addUser(user6);

	    User user7 = new User(7, "Roberto Souza", "Rua G, 246", "(81)97890-1234", "roberto@gmail.com", "roberto.souza", "senhabcd");
	    userController.addUser(user7);

	    User user8 = new User(8, "Fernanda Santos", "Rua H, 135", "(32)98901-2345", "fernanda@gmail.com", "fernanda.santos", "senhaefg");
	    userController.addUser(user8);

	    User user9 = new User(9, "Antônio Oliveira", "Rua I, 369", "(31)99012-3456", "antonio@gmail.com", "antonio.oliveira", "senhahij");
	    userController.addUser(user9);

	    User user10 = new User(10, "Luana Silva", "Rua J, 258", "(43)90123-4567", "luana@gmail.com", "luana.silva", "senhaklm");
	    userController.addUser(user10);
		
		launch(args);
	}
}
