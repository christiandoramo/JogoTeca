package br.jogoteca.system.application;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.Genre;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			});
			mb.getItems().add(item);
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

	public static void main(String[] args) {
		launch(args);
	}

}
