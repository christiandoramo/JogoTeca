package br.ufrpe.flight_system.gui;

import java.util.List;

import br.ufrpe.flight_system.negocio.beans.Bilhete;
import br.ufrpe.flight_system.negocio.beans.Voo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaPassageirosPorVooScreenController {
    
    @FXML private TableColumn<Bilhete, String> columnNome;
    @FXML private TableColumn<Bilhete, String> columnUltimoNome;
    @FXML private TableColumn<Bilhete, String> columnCPF;
    @FXML private TableColumn<Bilhete, String> columnPassaporte;
    @FXML private TableColumn<Bilhete, String> columnAssento;
    @FXML private TableView<Bilhete> tblViewPassageiros;
    @FXML private Label lblTitulo;
    
    private Voo vooCorrente;
    
    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("passageiroNome"));
        columnUltimoNome.setCellValueFactory(new PropertyValueFactory<>("passageiroUltimoNome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("passageiroCpf"));
        columnPassaporte.setCellValueFactory(new PropertyValueFactory<>("passageiroPassaporte"));       
        columnAssento.setCellValueFactory(new PropertyValueFactory<>("idAssento"));
    }

    public void configurarListaBilhetesDoVoo(List<Bilhete> passageiros) {
        ObservableList<Bilhete> result = FXCollections.observableArrayList();
        result.addAll(passageiros);
        tblViewPassageiros.setItems(result);
    }

    public void setVooCorrente(Voo vooCorrente) {
        this.vooCorrente = vooCorrente;
        this.lblTitulo.setText("Passageiros do Voo '" + this.vooCorrente.getOrigem() + "' -> '" + this.vooCorrente.getDestino() + "'");
    }
}
