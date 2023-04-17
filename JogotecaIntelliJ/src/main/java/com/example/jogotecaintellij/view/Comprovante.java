
package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Venda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Comprovante extends ViewController implements Initializable {
    @FXML
    protected TableColumn<Venda, String> columncpf;

    @FXML
    protected TableColumn<Venda, String> columndados;

    @FXML
    protected TableColumn<Venda, String> columnidPedido;

    @FXML
    protected TableColumn<Venda, String> columnidVenda;

    @FXML
    protected TableColumn<Venda, String> columnmetodoPagamento;

    @FXML
    protected TableColumn<Venda, String> columnmomento;

    @FXML
    protected TableColumn<Venda, String> columnnomeComprador;

    @FXML
    protected TableColumn<Venda, String> columnnomesJogos;

    @FXML
    protected TableColumn<Venda, String> columnvalor;

    @FXML
    protected TableView<Venda> tabelaComprovante;

    @FXML
    private Label comprovanteLog;
    @FXML
    private Button btnPerfilDojogo;
    private List<String> linhasComprovante;

    @FXML
    void baixarComprovante(ActionEvent event) throws IOException {
        if (!linhasComprovante.isEmpty()) {
            String filePath = "comprovante_" + suc.getVendaCorrente().getId() + ".txt";
            FileWriter writer = new FileWriter(filePath);
            for (String linha : linhasComprovante) {
                writer.write(linha + "\n");
            }
            writer.close();
            comprovanteLog.setText("O comprovante foi baixado");
        } else
            comprovanteLog.setText("Você já baixou o comprovante");
        comprovanteLog.setVisible(true);
    }

    @FXML
    void sairParaMeusJogos(ActionEvent event) throws IOException, ElementDoesNotExistException {
        irParaMeusJogos(event);
        suc.setItemCorrente(null);
        suc.setItensCorrentes(null);
        suc.atualizarWishlistPosCompra();
    }

    @FXML
    void sairParaMeusPedidos(ActionEvent event) throws IOException, ElementDoesNotExistException {
        irParaMeusPedidos(event);
        suc.setItemCorrente(null);
        suc.setItensCorrentes(null);
        suc.atualizarWishlistPosCompra();
    }

    @FXML
    void sairParaPerfilDoJogo(ActionEvent event) throws IOException, ElementDoesNotExistException {
        irParaPerfilDoJogo(event);
        suc.setItensCorrentes(null);
        suc.atualizarWishlistPosCompra();
    }

    private void carregarComprovante(Venda _venda) {
        linhasComprovante.clear();
        try {
            ObservableList<Venda> observableListVenda = FXCollections.observableList(Collections.singletonList(suc.getVendaCorrente()));
            tabelaComprovante.setItems(observableListVenda);
            columnidVenda.setCellValueFactory(cellData -> {
                linhasComprovante.add("Id da Venda: " + Integer.toString(_venda.getId()));
                return new SimpleStringProperty(Integer.toString(_venda.getId()));
            });
            columnidPedido.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                linhasComprovante.add("Id do Pedido: " + Integer.toString(_venda.getPedido().getId()));
                return new SimpleStringProperty(Integer.toString(_venda.getPedido().getId()));
            });
            columnnomesJogos.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                List<ItemJogo> itens = _venda.getPedido().getItens();
                StringBuilder nomes = new StringBuilder();
                for (ItemJogo item : itens)
                    nomes.append(item.getName()).append("\n");
                linhasComprovante.add("Nome do(s) jogo(s): " + nomes.toString().replace("\n"," | "));
                return new SimpleStringProperty(nomes.toString());
            });
            columnmomento.setCellValueFactory(cellData -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
                linhasComprovante.add("Momento da Compra: " + _venda.getPedido().getVencimento().format(formatter));
                return new SimpleStringProperty(_venda.getPedido().getVencimento().format(formatter));
            });
            columnvalor.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                linhasComprovante.add("Valor Total: " + String.format("%.2f", _venda.getPedido().totalValue()));
                return new SimpleStringProperty(String.format("%.2f", _venda.getPedido().totalValue()));
            });
            columnmetodoPagamento.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                linhasComprovante.add("Método do Pagamento: " + _venda.getPedido().getMetodo().name());
                return new SimpleStringProperty(_venda.getPedido().getMetodo().name());
            });
            columncpf.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                linhasComprovante.add("CPF do usuário: " + _venda.getPedido().getUser().getCpf());
                return new SimpleStringProperty(_venda.getPedido().getUser().getCpf());
            });
            columnnomeComprador.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                linhasComprovante.add("Nome do usuário: " + _venda.getPedido().getUser().getNome());
                return new SimpleStringProperty(_venda.getPedido().getUser().getNome());
            });
            columndados.setCellValueFactory(cellData -> {
                linhasComprovante.add("\n");
                List<String> listaDeDados = _venda.getDadosBancarios();
                StringBuilder builderDeDados = new StringBuilder();
                if (listaDeDados != null)
                    for (String dado : listaDeDados)
                        builderDeDados.append(dado).append("\n");
                linhasComprovante.add("Dado(s) Bancário(s): " + builderDeDados.toString().replace("\n"," | "));
                return new SimpleStringProperty(builderDeDados.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnPerfilDojogo.setVisible(suc.getItemCorrente() != null);
        linhasComprovante = new ArrayList<>();
        comprovanteLog.setVisible(false);
        carregarComprovante(suc.getVendaCorrente());
    }
}
