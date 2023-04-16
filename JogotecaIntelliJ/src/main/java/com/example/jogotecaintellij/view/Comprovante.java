
package com.example.jogotecaintellij.view;

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
        if (linhasComprovante != null) {
            String filePath = "comprovante_" + suc.getVendaCorrente().getId() + ".txt";
            FileWriter writer = new FileWriter(filePath);
            for (String linha : linhasComprovante) {
                writer.write(linha + "\n");
            }
            writer.close();
            linhasComprovante = null;
            comprovanteLog.setText("O comprovante foi baixado");
        } else
            comprovanteLog.setText("Você já baixou o cromprovante");
        comprovanteLog.setVisible(true);
    }

    @FXML
    void sairParaMeusJogos(ActionEvent event) throws IOException {
        irParaMeusJogos(event);
        suc.setItemCorrente(null);
        suc.setItensCorrentes(null);
    }

    @FXML
    void sairParaMeusPedidos(ActionEvent event) throws IOException {
        irParaMeusPedidos(event);
        suc.setItemCorrente(null);
        suc.setItensCorrentes(null);
    }

    @FXML
    void sairParaPerfilDoJogo(ActionEvent event) throws IOException {
        irParaPerfilDoJogo(event);
        suc.setItensCorrentes(null);
    }

    private void carregarComprovante(Venda _venda) {
        try {
            ObservableList<Venda> observableListVenda = FXCollections.observableList(Collections.singletonList(suc.getVendaCorrente()));
            tabelaComprovante.setItems(observableListVenda);
            columnidVenda.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(Integer.toString(venda.getId()));
                return new SimpleStringProperty(Integer.toString(venda.getId()));
            });
            columnidPedido.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(Integer.toString(venda.getPedido().getId()));
                return new SimpleStringProperty(Integer.toString(venda.getPedido().getId()));
            });
            columnnomesJogos.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                List<ItemJogo> itens = venda.getPedido().getItens();
                StringBuilder nomes = new StringBuilder();
                for (ItemJogo item : itens)
                    nomes.append(item.getName()).append("\n");
                linhasComprovante.add(nomes.toString());
                return new SimpleStringProperty(nomes.toString());
            });
            columnmomento.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "BR"));
                linhasComprovante.add(venda.getPedido().getVencimento().format(formatter));
                return new SimpleStringProperty(venda.getPedido().getVencimento().format(formatter));
            });
            columnvalor.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(String.format("%.2f", venda.getPedido().totalValue()));
                return new SimpleStringProperty(String.format("%.2f", venda.getPedido().totalValue()));
            });
            columnmetodoPagamento.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(venda.getPedido().getMetodo().name());
                return new SimpleStringProperty(venda.getPedido().getMetodo().name());
            });
            columncpf.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(venda.getPedido().getUser().getCPF());
                return new SimpleStringProperty(venda.getPedido().getUser().getCPF());
            });
            columnnomeComprador.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                linhasComprovante.add(venda.getPedido().getMetodo().name());
                return new SimpleStringProperty(venda.getPedido().getMetodo().name());
            });
            columndados.setCellValueFactory(cellData -> {
                Venda venda = cellData.getValue();
                List<String> listaDeDados = venda.getDadosBancarios();
                StringBuilder builderDeDados = new StringBuilder();
                builderDeDados.append("");
                if (listaDeDados != null)
                    for (String dado : listaDeDados)
                        builderDeDados.append(dado).append("\n");
                linhasComprovante.add(builderDeDados.toString());
                return new SimpleStringProperty(builderDeDados.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (suc.getItemCorrente() == null) {
            btnPerfilDojogo.setVisible(false);
        } else {
            btnPerfilDojogo.setVisible(true);
        }
        linhasComprovante = new ArrayList<>();
        comprovanteLog.setVisible(false);
        carregarComprovante(suc.getVendaCorrente());
    }
}
