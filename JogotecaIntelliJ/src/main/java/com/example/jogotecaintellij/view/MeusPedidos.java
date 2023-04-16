package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.PedidoController;
import com.example.jogotecaintellij.controller.VendaController;
import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Venda;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    private TableColumn<Pedido, Void> columnAcao;
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
                    nomes.append(item.getName()).append("\n");
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
        // o id de pedido ja fica pronto pra vizualizar o pedido ou o pagamento logo abaixo
        // Caso nosso sistema permitisse pagamentos depois do pagamento usariamos a mesma função abaixo
        // ou ate poderiamos mudar o botao que aparece e sua ação com base enum de pedido OrderStatus - PAGO-ESPERANDOPAGAMENTO
        // se PAGO - O botão seria vizualizar compra. ESPERANDOPAGAMENTO - Pagar o pedido agora
        columnAcao.setCellFactory(new Callback<TableColumn<Pedido, Void>, TableCell<Pedido, Void>>() {
            @Override
            public TableCell<Pedido, Void> call(TableColumn<Pedido, Void> param) {
                final TableCell<Pedido, Void> cell = new TableCell<Pedido, Void>() {
                    private final Button btn = new Button("Ver Compra");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                                PedidoController pc = PedidoController.getInstance();
                                VendaController vc = VendaController.getInstance();
                                // É o usado o id do pedido e não da venda pos caso o sistema permitisse
                                // a compra de pedidos EsperandoPagamento, o Pedido ainda seria pago e criaria a venda
                                int idDoPedido = Integer.parseInt(colunaId.getCellData(getIndex()));
                                // justamente esse getIndex é o index da TableCell
                                Pedido pedidoAtual = pc.buscarPeloId(idDoPedido);
                                suc.setPedidoCorrente(pedidoAtual);
                                Venda vendaAtual = vc.searchVendaByPedidoId(idDoPedido);
                                suc.setVendaCorrente(vendaAtual);
                                Pedido pedido = getTableView().getItems().get(getIndex());
                                System.out.println("Botão em ver compra Funcionando ");
                                irParaComprovante(event);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });

    }
}