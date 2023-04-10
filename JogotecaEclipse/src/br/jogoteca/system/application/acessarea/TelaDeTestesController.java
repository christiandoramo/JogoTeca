package br.jogoteca.system.application.acessarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TelaDeTestesController extends AcessAreaController implements Initializable {
	@FXML
	private void OnGoToLoginButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaLogin(event);
	}

	@FXML
	public void OnGoToRegisterButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaCadastro(event);
	}

	@FXML
	protected void OnGoToCRUDJogosButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaCRUDJogos(event);
	}

	@FXML
	protected void OnGoToMenuAdminButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaMenuAdmin(event);
	}

	@FXML
	void OnGoToPagamentoButtonAction(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaPagamento(event);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}