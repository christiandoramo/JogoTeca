package br.jogoteca.system.application.acessarea;

import java.net.URL;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.UserController;
import br.jogoteca.system.exceptions.CredenciaisIncorretasException;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController extends AcessAreaController implements Initializable {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button registerButton;

	@FXML
	private Label errorMessageLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorMessageLabel.setVisible(false);
	}

	@FXML
	public void handleLoginButtonAction(ActionEvent event) throws IOException {
		UserController uc = UserController.getInstance();

		String username = usernameField.getText();
		String password = passwordField.getText();
		System.out.println(username);
		System.out.println(password);
		try {
			if (username.equals("admin") && password.equals("admin")) {
				errorMessageLabel.setVisible(false);
				setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
				irParaMenuAdmin(event);
			} else if (uc.checaLoginESenha2(username, password)) {
				errorMessageLabel.setVisible(false);
				usuarioAtual = uc.searchUserByLogin2(username);
				setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
				irParaFeedUsuario(event);
			} else {
				throw new CredenciaisIncorretasException();
			}
		} catch (Exception e) {
			// mostrar mensagem de erro caso ocorra
			errorMessageLabel.setText("Usuário e/ou senha inválidos");
			errorMessageLabel.setVisible(true);
			e.printStackTrace();
		}
	}

	@FXML
	public void OnRegisterButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaCadastro(event);
	}
}