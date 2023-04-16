package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Pedido;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.String.format;

public class MeusPedidos extends ViewController implements Initializable {
    @FXML
    private TableView<Pedido> tabelaMeusPedidos;
    @FXML
    private TableColumn<Pedido, String> colunaId;
    @FXML
    private TableColumn<Pedido, String> colunaItensJogosNomes;
    @FXML
    private TableColumn<Pedido, String> colunaVencimento;
    @FXML
    private TableColumn<Pedido, String> colunaPrecoPedido;
    @FXML
    private TableColumn<Pedido, String> colunaMetodo;
    @FXML
    private TableColumn<Pedido, String> colunaStatus;
    @FXML
    private Label meusPedidoLog;

    @FXML
    void voltarAoMeuPerfil(ActionEvent event) throws IOException {
        irParaPerfilDoUsuario(event);
    }

    // IMPLEMENTAR POR ULTIMO SE NECESSÁRIO UM METODO PARA PAGAR UM PEDIDO NÃO PAGO

    private void carregaMeusPedidos() {
        try {
            ObservableList<Pedido> observableListPedidos = FXCollections.observableList(suc.buscarListaPedidosUsuarioAtual());
            tabelaMeusPedidos.setItems(observableListPedidos);
            meusPedidoLog.setVisible(false);
            colunaId.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                return new SimpleStringProperty(Integer.toString(pedido.getId()));
            });
            colunaItensJogosNomes.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                List<ItemJogo> itens = pedido.getItens();
                StringBuilder nomes = new StringBuilder();
                for (ItemJogo item : itens)
                    nomes.append(item.getName()).append(", ");
                if (nomes.length() > 0)
                    nomes.delete(nomes.length() - 2, nomes.length()); // remove a vírgula e espaço do final, por isso o -2
                return new SimpleStringProperty(nomes.toString());
            });
            colunaVencimento.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
                return new SimpleStringProperty(pedido.getVencimento().format(formatter));
            });
            colunaPrecoPedido.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                return new SimpleStringProperty(String.format("%.2f", pedido.totalValue()));
            });
            colunaMetodo.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                return new SimpleStringProperty(pedido.getMetodo().name());
            });
            colunaStatus.setCellValueFactory(cellData -> {
                Pedido pedido = cellData.getValue();
                return new SimpleStringProperty(pedido.getStatus().name());
            });

        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                meusPedidoLog.setText("Nenhum pedido feito");
                meusPedidoLog.setVisible(true);
            } else
                e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaMeusPedidos();
    }
}
