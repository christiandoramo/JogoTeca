package br.jogoteca.system.application.acessarea;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.PedidoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PagamentosViewController extends AcessAreaController implements Initializable {

	
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

    PedidoController pc = PedidoController.getInstance();
    
    @FXML
    void butaoMudaTela(ActionEvent event) throws IOException{
    	//setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	//handleBotaoIrFiscal(event);
    }

    @FXML
    void butaoPagamento(ActionEvent event) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("confirmando");
    		alert.setContentText("Pagamento confirmado!");
    		Optional<ButtonType> result = alert.showAndWait();
    		
    }

    @FXML
    void butaoVolta(ActionEvent event) throws IOException{
    	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	handleBotaoIrParaCRUDJogos(event);
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
