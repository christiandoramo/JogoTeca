package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.PedidoController;
import com.example.jogotecaintellij.controller.VendaController;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Venda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsultaVendas extends ViewController implements Initializable {

    @FXML
    private Label logBusca;

    @FXML
    private Label logBuscas;

    @FXML
    private TableView<Venda> tabelaMinahsVendas;

    @FXML
    private TableColumn<Venda, String> colunaId;

    @FXML
    private TableColumn<Venda, String> colunaNomes;

    @FXML
    private TableColumn<Venda, String> colunaValor;

    @FXML
    private TableColumn<Venda, String> colunaMetodo;

    VendaController vc = VendaController.getInstance();


    private void carregaVenda() {
        try {
            ObservableList<Venda> observableListVendas = FXCollections.observableList(vc.searchAllVendas());
            tabelaMinahsVendas.setItems(observableListVendas);
            logBuscas.setVisible(false);
            colunaId.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                return new SimpleStringProperty(Integer.toString(venda.getPedido().getId()));
            });
            colunaNomes.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                List<ItemJogo> itens = venda.getPedido().getItens();
                StringBuilder nomes = new StringBuilder();
                for (ItemJogo item : itens)
                    nomes.append(item.getName()).append("\n");
                return new SimpleStringProperty(nomes.toString());
            });
            colunaValor.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                return new SimpleStringProperty(String.format("%.2f", venda.getPedido().totalValue()));
            });
            colunaMetodo.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                return new SimpleStringProperty(venda.getPedido().getMetodo().name());
            });
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {

                logBuscas.setText("Nenhum pedido feito");
                logBuscas.setVisible(true);
            } else
                e.printStackTrace();
        }
    }

    @FXML
    void voltarAdmin(ActionEvent event) throws IOException {
        irParaTela(event, "MenuAdmin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaVenda();
    }
}
