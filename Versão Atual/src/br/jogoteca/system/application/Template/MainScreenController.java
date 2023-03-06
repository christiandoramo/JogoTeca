package br.ufrpe.flight_system.gui;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import br.ufrpe.flight_system.negocio.Fachada;
import br.ufrpe.flight_system.negocio.beans.Cidade;
import br.ufrpe.flight_system.negocio.beans.Passageiro;
import br.ufrpe.flight_system.negocio.beans.Voo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("all")
public class MainScreenController {
    
    @FXML private BorderPane paneBilhetes;
    @FXML private BorderPane panePassageiros;
    @FXML private Button btnAddBilhete;
    @FXML private Button btnRemoveBilhete;
    @FXML private Button btnUpdateBilhete;
    
    @FXML private BorderPane paneVoos;
    @FXML private Button btnAddVoo;
    @FXML private Button btnRemoveVoo;
    @FXML private Button btnUpdateVoo;
    
    @FXML private TableColumn<Voo, Cidade> columnVooOrigem;
    @FXML private TableColumn<Voo, Cidade> columnVooDestino;
    @FXML private TableColumn<Voo, String> columnVooSaida;
    @FXML private TableColumn<Voo, String> columnVooChegada;
    @FXML private TableView<Voo> tblViewVoos;
    
    @FXML private TableColumn<Passageiro, String> columnNome;
    @FXML private TableColumn<Passageiro, String> columnUltimoNome;
    @FXML private TableColumn<Passageiro, String> columnCPF;
    @FXML private TableColumn<Passageiro, String> columnPassaporte;
    @FXML private TableView<Passageiro> tblViewPassageiros;
    
    @FXML
    public void initialize(){
        String image = MainScreenController.class.getResource("/airplane_after.jpg").toExternalForm();
        paneVoos.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        
        String image2 = MainScreenController.class.getResource("/ts-airport.jpg").toExternalForm();
        panePassageiros.setStyle("-fx-background-image: url('" + image2 + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        
        columnVooOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        columnVooDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        columnVooSaida.setCellValueFactory(
            saida -> {
                SimpleStringProperty property = new SimpleStringProperty();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");
                property.setValue(dateFormat.format(saida.getValue().getHorarioSaida()));
                return property;
         });

        columnVooChegada.setCellValueFactory(
            chegada -> {
               SimpleStringProperty property = new SimpleStringProperty();
               DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");
               property.setValue(dateFormat.format(chegada.getValue().getHorarioEstimadoChegada()));
               return property;
        });
        
        this.updateListaVoos();
        tblViewVoos.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnUltimoNome.setCellValueFactory(new PropertyValueFactory<>("ultimoNome"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnPassaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));
        
        this.updateListaPassageiros();
        tblViewPassageiros.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
    
    @FXML
    public void btnUpdateVooClicked(ActionEvent event) {
        // IMPLEMENTAÇÃO NÃO NECESSÁRIA PARA ESTA AVALIAÇÃO       
    }
    
    @FXML
    public void btnAddVooClicked(ActionEvent event) {
        Stage dialog = new Stage();

        dialog.setScene(ScreenManager.getInstance().getAddVooScene());
        dialog.setResizable(false);
        dialog.setTitle("Adicionar novo voo");
        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }
    
    @FXML
    public void btnRemoveVooClicked(ActionEvent event) {
        Voo v = tblViewVoos.getSelectionModel().getSelectedItem();
        if (v != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setTitle("Confirmação de exclusão de voo");
            alert.setContentText("Tem certeza que deseja remover voo a seguir?\n" + v);
            Optional<ButtonType> btnPressionado = alert.showAndWait();
            if (btnPressionado.isPresent()
                    && btnPressionado.get().equals(ButtonType.OK)) {
                tblViewVoos.getItems().remove(
                        tblViewVoos.getSelectionModel().getSelectedIndex());
            } 
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um voo antes de tentar exclui-lo");
            alert.show();
        }
    }
    
    @FXML
    public void btnListarPassageirosClicked(ActionEvent event) {
        Voo v = tblViewVoos.getSelectionModel().getSelectedItem();
        if (v != null) {
            Stage dialog = new Stage();

            dialog.setScene(ScreenManager.getInstance().getListaPassageirosScene());
            ScreenManager.getInstance().getListaPassageirosPorVooScreenController().configurarListaBilhetesDoVoo(
                    Fachada.getInstance().listarBilhetesPorVoo(v));
            ScreenManager.getInstance().getListaPassageirosPorVooScreenController().setVooCorrente(v);
            dialog.setResizable(false);
            dialog.setTitle("Lista de passageiros");
            dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um voo antes de listar passageiros");
            alert.show();
        }
    }
    
    @FXML
    public void btnEmitirBilheteClicked(ActionEvent event) {
        Voo v = tblViewVoos.getSelectionModel().getSelectedItem();
        if (v != null) {
            Stage dialog = new Stage();

            dialog.setScene(ScreenManager.getInstance().getEmitirBilheteScene());
            ScreenManager.getInstance().getEmitirBilheteScreenController().configurarPassageiros(
                    Fachada.getInstance().listarPassegeiros());
            ScreenManager.getInstance().getEmitirBilheteScreenController().atualizarVooSelecionado(v);
            dialog.setResizable(false);
            dialog.setTitle("Bilhete para voo de '" + v.getOrigem() + "' para '" + v.getDestino() + "'");
            dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um voo antes de listar passageiros");
            alert.show();
        }
    }
    
    @FXML
    public void btnAddPassageiroClicked(ActionEvent event) {
        Stage dialog = new Stage();

        dialog.setScene(ScreenManager.getInstance().getAddPassageirosScene());
        dialog.setResizable(false);
        dialog.setTitle("Adicionar novo passageiro");
        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }
    
    @FXML
    public void btnRemovePassageiroClicked(ActionEvent event) {
        Passageiro p = tblViewPassageiros.getSelectionModel().getSelectedItem();
        if (p != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setTitle("Confirmação de exclusão de passageiro");
            alert.setContentText("Tem certeza que deseja remover passageiro a seguir?\n" + p);
            Optional<ButtonType> btnPressionado = alert.showAndWait();
            if (btnPressionado.isPresent()
                    && btnPressionado.get().equals(ButtonType.OK)) {
                // TODO remover passageiro do repositório através do controlador e tratar exceções mostrando mensagem de erro (Alert)
                // Se houver exceções caso o passageiro tenha bilhetes emitidos 
                // em seu nome, item não deve ser removido da tela, ou seja, a 
                // linha de código abaixo somente poderá ser executada se o 
                // passageiro já estiver sido removido do repositório 
                tblViewPassageiros.getItems().remove(
                        tblViewPassageiros.getSelectionModel().getSelectedIndex());
            } 
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um passageiro antes de tentar exclui-lo");
            alert.show();
        }
    }
    
    @FXML
    public void btnAtualizarPassageiroClicked(ActionEvent event) {
        // IMPLEMENTAÇÃO NÃO NECESSÁRIA PARA ESTA AVALIAÇÃO
    }
    
    public void updateListaVoos() {        
        ObservableList<Voo> result = FXCollections.observableArrayList();
        result.addAll(Fachada.getInstance().listarVoos());        
        tblViewVoos.setItems(result);
    }
    
    public void updateListaPassageiros() {
        ObservableList<Passageiro> result = FXCollections.observableArrayList();
        result.addAll(Fachada.getInstance().listarPassegeiros());
        this.tblViewPassageiros.setItems(result);
    }
    
}
