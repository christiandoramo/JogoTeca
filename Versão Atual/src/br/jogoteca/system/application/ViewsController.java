package br.jogoteca.system.application;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import br.jogoteca.system.controllers.GameItemControllers;
import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.controllers.UserController;
import br.jogoteca.system.models.GameItem;
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
			log.setText("Digite um Nome");
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
			log.setText("Digite um Nome");
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

		//testando - OK
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<Game> games2 = new ArrayList<Game>();
		Game game1 = new Game(10, "LEAGUE", LocalDate.now(), Genre.Action, "description1", "imageURL1", 2.5);
		Game game2 = new Game(11, "LOL", LocalDate.now(), Genre.Action, "description2", "imageURL2", 3.5);
		Game game3 = new Game(12, "PWI", LocalDate.now(), Genre.RPG, "description3", "imageURL3", 4.5);
		
		Game game4 = new Game(13, "PWBR", LocalDate.now(), Genre.RPG, "description4", "imageURL4", 5.5);
		Game game5 = new Game(14, "PWHITS", LocalDate.now(), Genre.RPG, "description5", "imageURL5", 6.5);
		Game game6 = new Game(15, "PWELITE", LocalDate.now(), Genre.RPG, "description6", "imageURL6", 7.5);
		
		games.add(game1);games.add(game2);games.add(game3);
		games2.add(game4);games2.add(game5);games2.add(game6);
		
		GameItem gi1 = new GameItem(1, 1.5, games);
		GameItem gi2 = new GameItem(2, 2.5, games2);
		
		GameItemControllers testController = new GameItemControllers();
		
		//create - OK
		testController.addGameItem(gi1);
		testController.addGameItem(gi2);
		//remove - OK
		testController.removeGameItem(gi1);
		//update - OK
		testController.attGameItem(gi1, gi2);
		//read - OK
		for (GameItem x : testController.showGameItemList())
			System.out.println(x);
		
		
		
				
		/*UserController userController = new UserController();
		// Criando os usuários
	    User user1 = new User(1, "João Silva", "Rua A, 123", "(81)91234-5678", "joao@gmail.com", "joao.silva", "123456");
	    User user2 = new User(2, "Maria Souza", "Rua B, 456", "(31)92345-6789", "maria@gmail.com", "maria.souza", "654321");
	    User user3 = new User(3, "José Santos", "Rua C, 789", "(47)93456-7890", "jose@gmail.com", "jose.santos", "senha123");
	    User user4 = new User(4, "Ana Paula", "Rua D, 321", "(11)94567-8901", "ana@gmail.com", "ana.paula", "senha456");
	    User user5 = new User(5, "Pedro Oliveira", "Rua E, 654", "(22)95678-9012", "pedro@gmail.com", "pedro.oliveira", "senha789");
	    User user6 = new User(6, "Carla Silva", "Rua F, 987", "(57)96789-0123", "carla@gmail.com", "carla.silva", "senhaabc");
	    User user7 = new User(7, "Roberto Souza", "Rua G, 246", "(81)97890-1234", "roberto@gmail.com", "roberto.souza", "senhabcd");
	    User user8 = new User(8, "Fernanda Santos", "Rua H, 135", "(32)98901-2345", "fernanda@gmail.com", "fernanda.santos", "senhaefg");
	    User user9 = new User(9, "Antônio Oliveira", "Rua I, 369", "(31)99012-3456", "antonio@gmail.com", "antonio.oliveira", "senhahij");
	    User user10 = new User(10, "Luana Silva", "Rua J, 258", "(43)90123-4567", "luana@gmail.com", "luana.silva", "senhaklm");
	    
	    // Inserindo os usuários no UserController
	    userController.insertUser(user1);
	    userController.insertUser(user2);
	    userController.insertUser(user3);
	    userController.insertUser(user4);
	    userController.insertUser(user5);
	    userController.insertUser(user6);
	    userController.insertUser(user7);
	    userController.insertUser(user8);
	    userController.insertUser(user9);
	    userController.insertUser(user10);
	    
	    for (User user : userController.getAllUsers())
			System.out.println(user);
	    
	    // Testando a função de busca por ID
	    User searchedUser = userController.searchUserById(1);
	    if (searchedUser != null) {
	        System.out.println("\nUsuário encontrado: " + searchedUser);
	    } else {
	        System.out.println("\nUsuário não encontrado.");
	    }
	    
	    searchedUser = userController.searchUserById(6);
	    if (searchedUser != null) {
	        System.out.println("Usuário encontrado: " + searchedUser);
	    } else {
	        System.out.println("Usuário não encontrado.");
	    }
	    
	    searchedUser = userController.searchUserById(12);
	    if (searchedUser != null) {
	        System.out.println("Usuário encontrado: " + searchedUser);
	    } else {
	        System.out.println("Usuário não encontrado.");
	    }
	    
	    // Testando a função de atualização por ID
	    User updatedUser = new User(1, "John Silver", "Rua A, 123", "(81)91234-5678", "john@gmail.com", "john.silver", "123456");
	    userController.updateUserById(1, updatedUser);
	    System.out.println("\nUsuário atualizado: " + userController.searchUserById(1));
	    
	    // Testando a função de remoção por ID
	    System.out.println("\nUsuário removido: " + userController.searchUserById(2));
	    userController.destroyUserById(2);
	    
	    System.out.println("Usuário removido: " + userController.searchUserById(5));
	    userController.destroyUserById(5);
	    
	    System.out.println("Usuário removido: " + userController.searchUserById(8));
	    userController.destroyUserById(8);
	    
	    // Testando a função de listar todos os usuários
	    ArrayList<User> allUsers = userController.getAllUsers();
	    System.out.println("\nTodos os usuários:");
	    for (User user : allUsers) {
	        System.out.println(user);
	    }
	    
	    searchedUser = userController.searchUserById(2);
	    if (searchedUser != null) {
	        System.out.println("\nUsuário encontrado: " + searchedUser);
	    } else {
	        System.out.println("\nUsuário não encontrado.\n");
	    }*/

		launch(args);
	}

}
