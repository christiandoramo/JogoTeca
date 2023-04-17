package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.PedidoController;
import com.example.jogotecaintellij.controller.VendaController;
import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PedidoPagamento extends ViewController implements Initializable {
    @FXML
    public Button btnperfil;
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
    void btnIrParaWishlist(ActionEvent event) throws IOException {
        irParaWishlist(event);
    }

    @FXML
    public void btnIrParaPerfilDoJogo(ActionEvent event) throws IOException {
        irParaPerfilDoJogo(event);
    }

    @FXML
    protected void butaoPagamento(ActionEvent event) {
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
                Pedido novoPedido = new Pedido(pc.novoPrazo(1), suc.getUsuarioCorrente(), suc.getItensCorrentes(), OrderStatus.PAGO, Metodo.DEBITO);
                pc.adicionarPedido(novoPedido);
                suc.setPedidoCorrente(novoPedido);
                Venda novaVenda = new Venda(novoPedido, nova);
                vc.insertVenda(novaVenda);
                suc.setVendaCorrente(novaVenda);
                suc.atualizarWishlist();
                irParaComprovante(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void butaoPagamento2(ActionEvent event) {
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
                Pedido novoPedido = new Pedido(pc.novoPrazo(1), suc.getUsuarioCorrente(), suc.getItensCorrentes(), OrderStatus.PAGO, Metodo.CREDITO);
                pc.adicionarPedido(novoPedido);
                suc.setPedidoCorrente(novoPedido);
                Venda novaVenda = new Venda(novoPedido, nova);
                vc.insertVenda(novaVenda);
                suc.setVendaCorrente(novaVenda);
                suc.atualizarWishlist();
                irParaComprovante(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void butaoPagamento3(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmando");
        alert.setContentText("Pagamento confirmado!");
        Optional<ButtonType> result = alert.showAndWait();
        try {
            Pedido novoPedido = new Pedido(pc.novoPrazo(1), suc.getUsuarioCorrente(), suc.getItensCorrentes(), OrderStatus.PAGO, Metodo.PIX);
            pc.adicionarPedido(novoPedido);
            suc.setPedidoCorrente(novoPedido);
            nova.add(suc.getUsuarioCorrente().getCpf());// O cpf é um exemplo de dado de um pix
            Venda novaVenda = new Venda(novoPedido, nova);
            vc.insertVenda(novaVenda);
            suc.setVendaCorrente(novaVenda);
            suc.atualizarWishlistPosCompra();
            irParaComprovante(event);
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
    }

    @FXML
    void butaoVolta2(ActionEvent event) throws IOException {
        setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        // precisa  do id do BOTAO -> vai ter que ficar invisivel se o user ter vindo pela wishlist
        irParaPerfilDoJogo(event);
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
        AnchorPane[] telasOperacionais = new AnchorPane[]{telaDebito, telaCredito, telaPix};
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

        nova = new ArrayList<>();
    }


}
