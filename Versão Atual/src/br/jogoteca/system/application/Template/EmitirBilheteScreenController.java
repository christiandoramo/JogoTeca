package br.ufrpe.flight_system.gui;

import java.util.List;

import br.ufrpe.flight_system.exception.AssentoInvalidoException;
import br.ufrpe.flight_system.exception.AssentoJaMarcadoException;
import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.negocio.Fachada;
import br.ufrpe.flight_system.negocio.beans.Bilhete;
import br.ufrpe.flight_system.negocio.beans.Passageiro;
import br.ufrpe.flight_system.negocio.beans.Voo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmitirBilheteScreenController {
    
    @FXML private TableColumn<Passageiro, String> columnNome;
    @FXML private TableColumn<Passageiro, String> columnUltimoNome;
    @FXML private TableColumn<Passageiro, String> columnCPF;
    @FXML private TableColumn<Passageiro, String> columnPassaporte;
    @FXML private TableView<Passageiro> tblViewPassageiros;
    
    @FXML private Button btnEmitir;
    @FXML private Button btnCancelar;
    
    @FXML private TextField txtAssento;
    
    private Voo vooSelecionado;
    
    @FXML
    public void initialize() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnUltimoNome.setCellValueFactory(new PropertyValueFactory<>("ultimoNome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnPassaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));       
    }
    
    @FXML
    public void btnEmitirClicked(ActionEvent event) {
        Passageiro p = this.tblViewPassageiros.getSelectionModel().getSelectedItem();
        Alert erro = new Alert(AlertType.ERROR);
        erro.setHeaderText("");
        erro.setTitle("Erro");
        if (p == null) {
            erro.setContentText("Selecione um passageiro antes de emitir um bilhete");
            erro.show();
            return;
        }
        
        if (this.txtAssento.getText() == null || this.txtAssento.getText().length() == 0) {
            erro.setContentText("Assento não preenchido");
            erro.show();
            return;
        }
        
        Bilhete emitido = null;
        try {
            int assento = Integer.parseInt(this.txtAssento.getText());
            emitido = Fachada.getInstance().emitirBilhete(p, this.vooSelecionado, assento);
        } catch (NumberFormatException e) {
            erro.setContentText("Assento inválido");
            erro.show();
            return;
        } catch (AssentoInvalidoException e) {
            erro.setContentText("Assento inválido para este Voo");
            erro.show();
            return;
        } catch (AssentoJaMarcadoException e) {
            erro.setContentText("Este assento já está marcado para este Voo");
            erro.show();
            return;
        } catch (ElementoJaExisteException e) {
            erro.setContentText("Bilhete já foi emitido");
            erro.show();
            return;
        }
        
        if (emitido != null) {
            Alert sucesso = new Alert(AlertType.INFORMATION);
            sucesso.setHeaderText("");
            sucesso.setTitle("Sucesso");
            sucesso.setContentText("Bilhete emitido com sucesso.\n"
                    + "====================================\n"
                    + "%s\n"
                    + "====================================" + emitido);
            sucesso.show();
        }
        ((Stage) this.btnCancelar.getScene().getWindow()).close();
        this.limparCampos();
    }
    
    @FXML
    public void btnCancelarClicked(ActionEvent event) {
        ((Stage) this.btnCancelar.getScene().getWindow()).close();
        this.limparCampos();
    }
    
    public void configurarPassageiros(List<Passageiro> passageiros) {
        ObservableList<Passageiro> result = FXCollections.observableArrayList();
        result.addAll(passageiros);
        tblViewPassageiros.setItems(result);
    }
    
    public void limparCampos() {
        this.txtAssento.setText("");
        this.tblViewPassageiros.getSelectionModel().clearSelection();
        this.vooSelecionado = null;
    }
    
    public void atualizarVooSelecionado(Voo v) {
        if (v != null) {
            this.vooSelecionado = v;            
        }
    }

}
