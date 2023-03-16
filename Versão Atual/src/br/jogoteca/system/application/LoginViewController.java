package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LoginViewController implements Initializable {
	@FXML
	private Label label;
	
	@FXML
	protected void pularTela(ActionEvent event) {
		System.out.println("Clickou");
		label.setText("CLickou");
		ViewsController.changeScreen("PrincipalAdminView");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}