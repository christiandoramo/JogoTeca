package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrincipalAdminViewController implements Initializable {
	@FXML
	private Label label;
	
	@FXML
	private void voltarTela(ActionEvent event) {
		System.out.println("Clickou");
		label.setText("CLickou");
		ViewsController.changeScreen("LoginView");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
