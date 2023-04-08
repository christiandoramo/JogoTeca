package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PagamentosViewController implements Initializable {

	
	@FXML
    private AnchorPane telaDebito;

    @FXML
    private TextField textValidadeDebito;

    @FXML
    private TextField textDataDebito;

    @FXML
    private TextField textNumeroDebito;

    @FXML
    private AnchorPane telaCredito;

    @FXML
    private TextField textNumeroCredito;

    @FXML
    private TextField TextDataCredito;

    @FXML
    private TextField textValidadeCredito;

    @FXML
    private AnchorPane telaPix;

    @FXML
    void butaoMudaTela(ActionEvent event) {

    }

    @FXML
    void butaoPagamento(ActionEvent event) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("confirmando");
    		alert.setContentText("Pagamento confirmado!");
    }

    @FXML
    void butaoVolta(ActionEvent event) {

    }

    @FXML
    void mostrarOpcoesCredito(ActionEvent event) {
    	telaCredito.setVisible(true);
    }

    @FXML
    void mostrarOpcoesDebito(ActionEvent event) {
    	telaDebito.setVisible(true);
    }

    @FXML
    void mostrarOpcoesPix(ActionEvent event) {
    	telaPix.setVisible(true);
    }

	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
