package br.jogoteca.system.application;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.controllers.UserController;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;
import br.jogoteca.system.models.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

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

	// apenas permite que sejam digitados doubles - FUNCIONANDO
	public static void controlaDouble(TextField tf) {
		Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
		UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
			String newText = change.getControlNewText();
			if (validDoubleText.matcher(newText).matches()) {
				return change;
			}
			return null;
		};
		TextFormatter<Double> doubleFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, doubleFilter);
		tf.setTextFormatter(doubleFormatter);
	}

	// apenas permite que sejam digitados int - FUNCIONANDO
	public static void controlaInteiro(TextField tf) {
		Pattern validIntText = Pattern.compile("-?\\d+");
		UnaryOperator<TextFormatter.Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			if (validIntText.matcher(newText).matches()) {
				return change;
			}
			return null;
		};
		TextFormatter<Integer> integerFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter);
		tf.setTextFormatter(integerFormatter);
	}

	// cria os menuItems baseados nos enums genre - FUNCIONANDO
	public static void preencheMenuGeneros(MenuButton mb) {
		Genre _genres[] = Genre.values();
		for (Genre genre : _genres) {
			MenuItem item = new MenuItem(genre.name());
			item.setUserData(genre);
			item.setOnAction(e -> {
				mb.setText(item.getText());
				mb.setUserData(genre);
			});
			mb.getItems().add(item);
		}
	}

	public static void searchGameByNome(GamesController gc, TextField campo, ListView<Game> lista, Label log) {
		String nome = campo.getText();
		List<Game> gamesAchados = new ArrayList<>();
		if (nome != null) {
			Game n = gc.searchGameByName(nome);
			if (n != null) {
				System.out.println("id: " + n.getId());
				System.out.println("name: " + n.getName());
				System.out.println("id: " + n.getPrice());
				System.out.println("name: " + n.getGenre().name());
				System.out.println("id: " + n.getReleaseDate());
				System.out.println("name: " + n.getDescription());
				gamesAchados.add(n);
				ViewsController.mostraAchados(lista, gamesAchados);
				gamesAchados.forEach(action -> System.out.println(action.getName()));
				log.setVisible(false);
			} else {
				log.setText("Jogo Não Encontrado");
				log.setVisible(true);
			}
		} else {
			log.setText("Digite um Nome válido");
			log.setVisible(true);
		}
	}

	public static void searchGameById(GamesController gc, TextField campo, ListView<Game> lista, Label log) {
		int id = Integer.parseInt(campo.getText());
		List<Game> gamesAchados = new ArrayList<>();
		if (id != 0) {
			Game n = gc.searchGameById(id);
			if (n != null) {
				System.out.println("id: " + n.getId());
				System.out.println("name: " + n.getName());
				System.out.println("id: " + n.getPrice());
				System.out.println("name: " + n.getGenre().name());
				System.out.println("id: " + n.getReleaseDate());
				System.out.println("name: " + n.getDescription());
				gamesAchados.add(n);
				ViewsController.mostraAchados(lista, gamesAchados);
				log.setVisible(false);
			} else {
				log.setText("Jogo Não Encontrado");
				log.setVisible(true);
			}
		} else {
			log.setText("Digite um id válido");
			log.setVisible(true);
		}
	}

	public static void mostraAchados(ListView<Game> listaJogos, List<Game> gamesAchados) {
		ObservableList<Game> data = FXCollections.observableArrayList();
		data.addAll(gamesAchados);

		listaJogos.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
			@Override
			public ListCell<Game> call(ListView<Game> param) {
				ListCell<Game> cell = new ListCell<Game>() {
					@Override
					protected void updateItem(Game achado, boolean btl) {
						super.updateItem(achado, btl);
						if (achado != null) {
							File file = new File(achado.getImageURL());
							String imagePath = file.toURI().toString();
							Image img = new Image(imagePath);
							ImageView imgview = new ImageView(img);
							imgview.setFitWidth(100);
							imgview.setFitHeight(100);
							setGraphic(imgview);
							setText(achado.getName());

						}
					}
				};
				return cell;
			}
		});
		listaJogos.setItems(data);
	}

	public static void desabilitarDatasFuturas(DatePicker dp) {
		dp.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date.isAfter(LocalDate.now())) {
					setDisable(true);
				}
			}
		});
	}

	public static void escolherImagem(TextField campoUrl) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir Arquivo");
		Stage stage = (Stage) campoUrl.getScene().getWindow();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters()
				.add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			String absolutePath = selectedFile.getAbsolutePath();
			campoUrl.setText(absolutePath);
		}
	}

	public static void main(String[] args) {

		UserController userController = new UserController();

	    User user1 = new User(1, "João Silva", "Rua A, 123", "(81)91234-5678", "joao@gmail.com", "joao.silva", "123456");;
	    userController.insertUser(user1);

	    User user2 = new User(2, "Maria Souza", "Rua B, 456", "(31)92345-6789", "maria@gmail.com", "maria.souza", "654321");
	    userController.insertUser(user2);

	    User user3 = new User(3, "José Santos", "Rua C, 789", "(47)93456-7890", "jose@gmail.com", "jose.santos", "senha123");
	    userController.insertUser(user3);

	    User user4 = new User(4, "Ana Paula", "Rua D, 321", "(11)94567-8901", "ana@gmail.com", "ana.paula", "senha456");
	    userController.insertUser(user4);

	    User user5 = new User(5, "Pedro Oliveira", "Rua E, 654", "(22)95678-9012", "pedro@gmail.com", "pedro.oliveira", "senha789");
	    userController.insertUser(user5);

	    User user6 = new User(6, "Carla Silva", "Rua F, 987", "(57)96789-0123", "carla@gmail.com", "carla.silva", "senhaabc");
	    userController.insertUser(user6);

	    User user7 = new User(7, "Roberto Souza", "Rua G, 246", "(81)97890-1234", "roberto@gmail.com", "roberto.souza", "senhabcd");
	    userController.insertUser(user7);

	    User user8 = new User(8, "Fernanda Santos", "Rua H, 135", "(32)98901-2345", "fernanda@gmail.com", "fernanda.santos", "senhaefg");
	    userController.insertUser(user8);

	    User user9 = new User(9, "Antônio Oliveira", "Rua I, 369", "(31)99012-3456", "antonio@gmail.com", "antonio.oliveira", "senhahij");
	    userController.insertUser(user9);

	    User user10 = new User(10, "Luana Silva", "Rua J, 258", "(43)90123-4567", "luana@gmail.com", "luana.silva", "senhaklm");
	    userController.insertUser(user10);
	    
	    for (User user : userController.getAllUsers())
			System.out.println(user);
	    
	    /*User retrievedUser = userController.searchUserById(1);
	    System.out.println("Usuário encontrado: " + retrievedUser.getNome());
	    
	    User updatedUser = new User(1, "John Doe Jr.", "Rua B, 456", "(11) 9876-5432", "johndoejr@example.com", "johndoe", "123456");
	    userController.updateUserById(1, updatedUser);
	    System.out.println("Usuário atualizado: " + userController.searchUserById(1).getNome());
	    
	    User retrievedUser2 = userController.searchUserById(1);
	    userController.destroyUserById(1);
	    System.out.println("Usuário removido: " + retrievedUser2.getNome());*/

		launch(args);
	}

}
