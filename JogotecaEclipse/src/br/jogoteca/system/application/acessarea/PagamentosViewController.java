package br.jogoteca.system.application.acessarea;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.PedidoController;
import br.jogoteca.system.controllers.VendaController;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Metodo;
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.User;
import br.jogoteca.system.models.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
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
    
    VendaController vc = VendaController.getInstance();
    
   
    private List<String> nova;
    
  
    
    
    @FXML
    void butaoMudaTela(ActionEvent event) throws IOException{
    	//setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
    	//handleBotaoIrFiscal(event);
    }

    @FXML
    void butaoPagamento(ActionEvent event) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
    	if(preencheuEntradasInsercao()) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("confirmando");
    		alert.setContentText("Pagamento confirmado!");
    		Optional<ButtonType> result = alert.showAndWait();
    		String numero = textNumeroDebito.getText();
    		String data = textNumeroDebito.getText();
    		String validade = textNumeroDebito.getText();
    		nova.add(numero);
    		nova.add(data);
    		nova.add(validade);
    		vc.insertVenda(pedidoAtual,nova);
    		pc.adicionarPedido(vencimentoAtual, itemAtual, usuarioAtual, Metodo.DEBITO);  
    	}
    }
    
    @FXML
    void butaoPagamento2(ActionEvent event) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
    	if(preencheuEntradasInsercao2()) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("confirmando");
		alert.setContentText("Pagamento confirmado!");
		Optional<ButtonType> result = alert.showAndWait();
		String numero1 = textNumeroDebito.getText();
		String data1 = textNumeroDebito.getText();
		String validade1 = textNumeroDebito.getText();
		nova.add(numero1);
		nova.add(data1);
		nova.add(validade1);
		vc.insertVenda(pedidoAtual, nova);
		pc.adicionarPedido(vencimentoAtual, itemAtual, usuarioAtual, Metodo.CREDITO);
    	}	
    }
    
    @FXML
    void butaoPagamento3(ActionEvent event) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("confirmando");
		alert.setContentText("Pagamento confirmado!");
		Optional<ButtonType> result = alert.showAndWait();
    	vc.insertVenda(pedidoAtual,dadoAtual);
    	pc.adicionarPedido(vencimentoAtual, itemAtual, usuarioAtual, Metodo.PIX);
    }
    
    public boolean preencheuEntradasInsercao() {
		for (Node node : telaDebito.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField) node;
				if (tf.getText().trim().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
    
    public boolean preencheuEntradasInsercao2() {
		for (Node node : telaCredito.getChildren()) {
			if (node instanceof TextField) {
				TextField tf = (TextField) node;
				if (tf.getText().trim().isEmpty()) {
					return false;
				}
			}
		}
		return true;
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
