package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.VendaController;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaVendas extends ViewController implements Initializable {
    @FXML
    private TextField textPedido;

    @FXML
    private TextField textNumero;

    @FXML
    private ListView<Venda> ListViewVenda;

    @FXML
    private Label logBusca;

    VendaController vc = VendaController.getInstance();

    @FXML
    void buscarCartao(ActionEvent event) {
        String numero = textNumero.getText();
        List<Venda> vendasAchados = new ArrayList<>();
        if (numero != null && !numero.equals("")) {
            try {
               Venda n = vc.searchVendaByNumero(textNumero.getText());
                if (n != null) {
                    vendasAchados.add(n);
                    ViewController.mostraVendasAchadas(ListViewVenda, vendasAchados);
                    logBusca.setVisible(false);
                    System.out.println(n.getDadosBancarios());
                } else
                    System.out.println("número de cartão não encontrado");
                    throw new ElementDoesNotExistException(n);
            } catch (Exception e) {
                logBusca.setText("ERRROO");
                logBusca.setVisible(true);
                if (e instanceof ElementDoesNotExistException) {
                    logBusca.setText("Erro: Venda não encontrada");
                } else {
                    logBusca.setText("Ocorreu um erro. Tente novamente.");
                }
            }
        } else {
            logBusca.setText("Erro: Digite um numero válido");
            logBusca.setVisible(true);
        }
    }
    @FXML
    void buscarPedido(ActionEvent event) {
        int id = Integer.parseInt(textPedido.getText());
        List<Venda> pedidosAchados = new ArrayList<>();
        if (id > 0) {
            try {
                Venda n = vc.searchVendaById(id);
                if (n != null) {
                    pedidosAchados.add(n);
                    ViewController.mostraVendasAchadas( ListViewVenda, pedidosAchados);
                    logBusca.setVisible(false);
                    System.out.println(n.getId());
                } else {
                    System.out.println("id nao achado");
                    throw new ElementDoesNotExistException(n);
                }
            } catch (Exception e) {
                if (e instanceof ElementDoesNotExistException) {
                    logBusca.setText("Erro: Usuario não encontrado");
                } else {
                    logBusca.setText("Ocorreu um erro. Tente novamente.");
                }
                logBusca.setVisible(true);
            }
        } else {
            logBusca.setText("Erro: Digite um ID válido");
            logBusca.setVisible(true);
        }
    }
    @FXML
    void searchTodos(ActionEvent event) {
        try {
            List<Venda> allVendas = vc.searchAllVendas();
            if (!allVendas.isEmpty()) {
                ViewController.mostraVendasAchadas( ListViewVenda, allVendas);
                logBusca.setVisible(false);
            } else
                throw new ElementsDoNotExistException(allVendas);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                logBusca.setText("Erro: Nenhuma Venda encontrada");
            } else {
                logBusca.setText("Ocorreu um erro. Tente novamente.");
            }
            logBusca.setVisible(true);
        }
    }
    @FXML
    void voltarAdmin(ActionEvent event) throws IOException {
        irParaTela(event, "MenuAdmin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
