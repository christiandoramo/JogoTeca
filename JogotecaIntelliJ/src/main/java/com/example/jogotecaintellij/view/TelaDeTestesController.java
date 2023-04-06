package com.example.jogotecaintellij.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TelaDeTestesController extends AccessAreaController implements Initializable {
    @FXML
    private void OnGoToLoginButtonAction(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        handleBotaoIrParaLogin(event);
    }
    @FXML
    public void OnGoToRegisterButtonAction(ActionEvent event) throws IOException {
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        handleBotaoCadastro(event);
    }
    
	@FXML
	protected void OnGoToCRUDJogosButtonAction(ActionEvent event) throws IOException {
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	handleBotaoIrParaCRUDJogos(event);
	}
    
	@FXML
	protected void OnGoToMenuAdminButtonAction(ActionEvent event) throws IOException {
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	handleBotaoIrMenuAdmin(event);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}