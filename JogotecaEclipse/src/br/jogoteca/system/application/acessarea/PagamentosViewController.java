package br.jogoteca.system.application.acessarea;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.PedidoController;
import br.jogoteca.system.controllers.VendaController;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.models.Metodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
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

	@FXML
	private MenuButton menuPagamento;

	PedidoController pc = PedidoController.getInstance();

	VendaController vc = VendaController.getInstance();

	private List<String> nova;

	String mAtualiza = "";
	String mRemove = "";

	@FXML
	void butaoMudaTela(ActionEvent event) throws IOException {
		// setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		// handleBotaoIrFiscal(event);
	}

	@FXML
	protected void butaoPagamento(ActionEvent event)
			throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
		if (preencheuEntradasInsercao()) {
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
			try {
				itensAtuais = Arrays.asList(itemAtual);
				pc.adicionarPedido(vencimentoAtual, itensAtuais, usuarioAtual, Metodo.DEBITO);
				pedidoAtual = pc.buscarTodos().get(pc.buscarTodos().size()-1);
				vc.insertVenda(pedidoAtual, nova);
				vendaAtual = vc.searchAllVendas().get(vc.searchAllVendas().size()-1);
				setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
				irParaComprovante(event);
				itemAtual = null;
				jogoAtual = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	protected void butaoPagamento2(ActionEvent event)
			throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
		if (preencheuEntradasInsercao2()) {
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
			try {
				itensAtuais = Arrays.asList(itemAtual);
				pc.adicionarPedido(vencimentoAtual, itensAtuais, usuarioAtual, Metodo.CREDITO);
				pedidoAtual = pc.buscarTodos().get(pc.buscarTodos().size()-1);
				vc.insertVenda(pedidoAtual, nova);
				vendaAtual = vc.searchAllVendas().get(vc.searchAllVendas().size()-1);
				setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
				irParaComprovante(event);
				itemAtual = null;
				jogoAtual = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	protected void butaoPagamento3(ActionEvent event)
			throws ElementAlreadyExistsException, ElementWithSameNameExistsException, IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("confirmando");
		alert.setContentText("Pagamento confirmado!");
		Optional<ButtonType> result = alert.showAndWait();
		try {
			itensAtuais = Arrays.asList(itemAtual);
			pc.adicionarPedido(vencimentoAtual, itensAtuais, usuarioAtual, Metodo.PIX);
			pedidoAtual = pc.buscarTodos().get(pc.buscarTodos().size()-1);
			vc.insertVenda(pedidoAtual, nova);
			vendaAtual = vc.searchAllVendas().get(vc.searchAllVendas().size()-1);
			setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
			irParaComprovante(event);
			itemAtual = null;
			jogoAtual = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	void butaoVolta(ActionEvent event) throws IOException {
		// setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		// handleBotaoIrParaWishList(event);
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaWishlist(event);
		itemAtual = null;
		jogoAtual = null;
		vendaAtual = null;
	}

	@FXML
	void butaoVolta2(ActionEvent event) throws IOException {
		setStage((Stage) ((Node) event.getSource()).getScene().getWindow());

		// ESSE BOTAO DEVE FICAR DESABILITADO OU INVISIVEL SE O USUARIO VINHER PELA TELA
		// DA WISHLIST
		// LOGO VAI PRECISAR DO id DO BOTAO
		irParaPerfilDoJogo(event);
		itemAtual = null;
		jogoAtual = null;
		vendaAtual = null;
		////////////////////////////////////////////////
	}

	@FXML
	protected void mostrarOpcoesCredito(ActionEvent event) {
		menuPagamento.setText("Crédito");
		limparOperacaoCRUD(telaDebito);

	}

	@FXML
	protected void mostrarOpcoesDebito(ActionEvent event) {
		menuPagamento.setText("Débito");
		limparOperacaoCRUD(telaCredito);

	}

	@FXML
	protected void mostrarOpcoesPix(ActionEvent event) {
		menuPagamento.setText("Pix");
		limparOperacaoCRUD(telaPix);

	}

	protected void limparOperacaoCRUD(AnchorPane telaOperacional) {
		AnchorPane[] telasOperacionais = new AnchorPane[] { telaDebito, telaCredito, telaPix };
		mAtualiza = "";
		mRemove = "";
		for (AnchorPane tela : telasOperacionais)
			if (tela.isVisible()) {
				tela.setVisible(false);
				for (Node node : telaOperacional.getChildren()) {
					if (node instanceof TextInputControl) {
						((TextInputControl) node).setText(null);
					} else if (node instanceof MenuButton) {
						((MenuButton) node).setText("Selecionar Genero");
					} else if (node instanceof DatePicker) {
						((DatePicker) node).setValue(null);
					} else if (node instanceof Label) {
						((Label) node).setVisible(false);
					}
				}
			}
		telaOperacional.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
