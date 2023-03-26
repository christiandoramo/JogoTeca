package br.jogoteca.system.application;

import java.net.URL;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.GameItemControllers;
import br.jogoteca.system.controllers.OrdersController;
import br.jogoteca.system.controllers.UserController;
import br.jogoteca.system.models.GameItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CarrinhoController implements Initializable {
	OrdersController oc = OrdersController.getInstance();
	GameItemControllers gic = GameItemControllers.getInstance();
	UserController uc = UserController.getInstance();
	
	@FXML
	protected ListView<GameItem> listaItemsJogos;
	@FXML
	protected Label carrinhoLog;
	
	@FXML
	protected void irParaTelaPedidoDeCompra() {
		//ViewsController.pedidoCompraAtual = oc.searchCurrentCarrinho(usuarioAtual);
		// ViewsController.changeScreen(Tela.PEDIDOCOMPRA);
	}

	@FXML
	protected void limparCarrinho() {
		//ViewsController.pedidoCompraAtual = oc.searchCurrentCarrinho(usuarioAtual);
//		Order carrinho = oc.searchCurrentCarrinho(usuarioAtual);
//		ViewsController.mostraItemsAchados(listaItemsJogos, carrinho.getItems());
	}

	@FXML
	protected void voltarParaTelaPrincipalUsuario() {
		ViewsController.changeScreen(Tela.PRINCIPALUSUARIO);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
