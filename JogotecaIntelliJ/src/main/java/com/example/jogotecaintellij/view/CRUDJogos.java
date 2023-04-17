package com.example.jogotecaintellij.view;

import com.example.jogotecaintellij.controller.JogoController;
import com.example.jogotecaintellij.enums.Genre;
import com.example.jogotecaintellij.enums.StatusItemJogo;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Jogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDJogos extends ViewController implements Initializable {

    // parte nova
    @FXML
    protected Label titulo;
    @FXML
    protected TextField desenvolvedora;
    @FXML
    protected TextField publicadora;
    @FXML
    protected TextField CampoTrocaVideo;
    @FXML
    protected TextField CampoTrocaDesenvolvedora;
    @FXML
    protected TextField CampoTrocaPublicadora;
    @FXML
    protected TextField urlVideo;
    // parte nova


    @FXML
    protected MenuButton menuItens;
    @FXML
    protected AnchorPane telaInserir;
    @FXML
    protected TextField name;
    @FXML
    protected MenuButton genres;
    @FXML
    protected TextArea description;
    @FXML
    protected TextField price;
    @FXML
    protected DatePicker releaseDate;
    @FXML
    protected TextField urlImage;
    @FXML
    protected AnchorPane telaBuscar;
    @FXML
    protected TextField CampoBuscarId;
    @FXML
    protected TextField CampoBuscarNome;
    @FXML
    protected ListView<Jogo> listaJogos;
    @FXML
    protected MenuButton CampoBuscarGenero;

    @FXML
    protected AnchorPane telaAtualizar;
    @FXML
    protected TextField CampoAtualizarPorId;
    @FXML
    protected TextField CampoAtualizarPorNome;
    @FXML
    protected TextField CampoTrocaNome;
    @FXML
    protected ListView<Jogo> JogoAAtualizar;
    @FXML
    protected MenuButton CampoTrocaGenero;
    @FXML
    protected TextField CampoTrocaImage;
    @FXML
    protected TextField CampoTrocaPrice;
    @FXML
    protected DatePicker CampoTrocaReleaseDate;
    @FXML
    protected TextArea CampoTrocaDescricao;

    @FXML
    protected AnchorPane telaRemover;
    @FXML
    protected TextField campoRemoverId;
    @FXML
    protected TextField campoRemoverNome;
    @FXML
    protected ListView<Jogo> listaRemover;

    @FXML
    protected Label createLog;
    @FXML
    protected Label readLog;
    @FXML
    protected Label updateLog;
    @FXML
    protected Label destroyLog;

    JogoController gc = JogoController.getInstance();


    //pequena gambiarra para facilitar se vai remover usando id ou nome
    String modoAtualizacao = "";
    String modoRemocao = "";

    @FXML
    protected void voltarParaMenuAdmin(ActionEvent event) throws IOException {
        limparTelaCRUD();
        irParaMenuAdmin(event);
    }


    @FXML
    protected void removerJogo() {
        if (modoRemocao.equals("id"))
            removerPorId();
        else if (modoRemocao.equals("name"))
            removerPorName();
    }

    protected void removerPorId() {
        remover(Integer.parseInt(campoRemoverId.getText()), null);
    }

    protected void removerPorName() {
        remover(0, campoRemoverNome.getText());
    }

    protected void remover(int _id, String _name) {
        try {
            if (_id != 0) gc.destroyGameById(_id);
            else if (_name != null) gc.destroyGameByName(_name);
            destroyLog.setVisible(false);
        } catch (Exception e) {
            destroyLog.setText(e.getMessage());
            destroyLog.setVisible(true);
        }
    }

    @FXML
    protected void atualizarJogo() {
        if (preencheuAlgumaEntradaAtualizacao()) {
            if (modoAtualizacao.equals("id"))
                atualizarPorId();
            else if (modoAtualizacao.equals("name"))
                atualizarPorName();
            else
                updateLog.setText("Erro: Preencha algum campo para atualizar");
            updateLog.setVisible(true);
        }
    }

    @FXML
    protected void buscarRemoverPorId() {
        ViewController.searchGameById(gc, campoRemoverId, listaRemover, destroyLog);
        modoRemocao = "id";
    }

    @FXML
    protected void buscarRemoverPorName() {
        ViewController.searchGameByNome(gc, campoRemoverNome, listaRemover, destroyLog);
        modoRemocao = "name";
    }

    @FXML
    protected void buscarAtualizarPorId() {
        ViewController.searchGameById(gc, CampoAtualizarPorId, JogoAAtualizar, updateLog);
        modoAtualizacao = "id";
    }

    @FXML
    protected void buscarAtualizarPorName() {
        ViewController.searchGameByNome(gc, CampoAtualizarPorNome, JogoAAtualizar, updateLog);
        modoAtualizacao = "name";
    }

    protected void atualizarPorId() {
        int _id = Integer.parseInt(CampoAtualizarPorId.getText());
        String _newName = CampoTrocaNome.getText();
        Genre _genero = (Genre) CampoTrocaGenero.getUserData();
        String p = CampoTrocaPrice.getText();
        Double _price = p == null || p.equals("") ? null : Double.parseDouble(p);
        String _descricao = CampoTrocaDescricao.getText();
        String _image = CampoTrocaImage.getText();
        LocalDate _release = CampoTrocaReleaseDate.getValue();
        try {
            if (!gc.contemNome(_newName)) {
                if (_id != 0)
                    gc.updateGameById(_id, _newName, _genero, _price, _descricao, _image, _release);
                updateLog.setText("Sucesso: O Jogo foi Atualizado");
            } else
                throw new ElementWithSameNameExistsException(_newName);
        } catch (Exception e) {
            updateLog.setText("Jogo com mesmo nome encontrado");
        } finally {
            updateLog.setVisible(true);
        }
    }

    protected void atualizarPorName() {
        String _name = CampoAtualizarPorNome.getText();
        String _newName = CampoTrocaNome.getText() != null ? CampoTrocaNome.getText() : "";
        Genre _genero = (Genre) CampoTrocaGenero.getUserData();
        String p = CampoTrocaPrice.getText();
        Double _price = p == null || p.equals("") ? null : Double.parseDouble(p);
        String _descricao = CampoTrocaDescricao.getText();
        String _image = CampoTrocaImage.getText();
        LocalDate _release = CampoTrocaReleaseDate.getValue();
        try {
            if (!gc.contemNome(_newName)) {
                if (_newName != null)
                    gc.updateGameByName(_name, _newName, _genero, _price, _descricao, _image, _release);
                updateLog.setText("Sucesso: O Jogo foi Atualizado");
            } else
                throw new ElementWithSameNameExistsException(_newName);
        } catch (Exception e) {
            updateLog.setText("Jogo com mesmo nome encontrado");
        } finally {
            updateLog.setVisible(true);
        }
    }

    @FXML
    protected void inserirJogo() {
        if (preencheuEntradasInsercao()) {
            String nome = name.getText();
            Genre genero = (Genre) genres.getUserData();
            System.out.println(genero.name());
            String descricao = description.getText();
            LocalDate lancamento = releaseDate.getValue();
            String imgUrl = urlImage.getText();
            Double preco = Double.parseDouble(price.getText());
            String videoUrl = urlVideo.getText();
            String dev = desenvolvedora.getText();
            String pub = publicadora.getText();
            try {
                if (!gc.contemNome(nome)) {
                    gc.insertGame(nome, lancamento, genero, descricao, pub, dev, preco, imgUrl, videoUrl);
                    createLog.setText("Sucesso: Jogo inserido com sucesso");
                } else
                    throw new ElementWithSameNameExistsException(nome);
            } catch (Exception e) {
                createLog.setText("Jogo com mesmo nome encontrado");
            }
            gc.mostrarGameRepository();
        } else {
            createLog.setText("Erro: Preencha todos os Campos");
        }
        createLog.setVisible(true);

    }

    @FXML
    protected void searchGameByIdentificador() {
        ViewController.searchGameById(gc, CampoBuscarId, listaJogos, readLog);
    }

    @FXML
    protected void searchGameByNome() {
        ViewController.searchGameByNome(gc, CampoBuscarNome, listaJogos, readLog);
    }

    @FXML
    protected void searchGameByGenero() {
        Genre genero = (Genre) CampoBuscarGenero.getUserData();
        if (genero != null) {
            try {
                List<Jogo> gamesAchados = gc.searchGamesByGenre(genero);
                if (!gamesAchados.isEmpty()) {
                    ViewController.mostraGamesAchados(listaJogos, gamesAchados);
                    readLog.setVisible(false);
                } else {
                    throw new ElementsDoNotExistException(gamesAchados);
                }
            } catch (Exception e) {
                if (e instanceof ElementsDoNotExistException) {
                    readLog.setText("Erro: Nenhum Jogo encontrado desse gênero");
                } else {
                    readLog.setText("Ocorreu um erro. Tente novamente.");
                }
                readLog.setVisible(true);
            }
        } else {
            readLog.setText("Erro: Nenhum genero foi selecionado");
            readLog.setVisible(true);
        }
    }

    @FXML
    protected void searchTodos() {
        try {
            List<Jogo> allGames = gc.searchAllGames();
            if (!allGames.isEmpty()) {
                ViewController.mostraGamesAchados(listaJogos, allGames);
                readLog.setVisible(false);
                System.out.println("Tem jogos em gc");
            } else
                throw new ElementsDoNotExistException(allGames);
        } catch (Exception e) {
            System.out.println("NÃO tem jogos em gc");
            if (e instanceof ElementsDoNotExistException) {
                readLog.setText("Erro: Nenhum Jogo encontrado");
            } else {
                readLog.setText("Ocorreu um erro. Tente novamente.");
            }
            readLog.setVisible(true);

        }
    }

    @FXML
    protected void mostrarOpcoesCREATE(ActionEvent event) {
        menuItens.setText("Inserir novo Jogo");
        limparOperacaoCRUD(telaInserir);
    }

    @FXML
    protected void mostrarOpcoesREAD(ActionEvent event) {
        menuItens.setText("Buscar Jogo");
        limparOperacaoCRUD(telaBuscar);
    }

    @FXML
    protected void mostrarOpcoesUPDATE(ActionEvent event) {
        menuItens.setText("Atualizar Jogo");
        limparOperacaoCRUD(telaAtualizar);
    }

    @FXML
    protected void mostrarOpcoesDESTROY(ActionEvent event) {
        menuItens.setText("Remover Jogo");
        limparOperacaoCRUD(telaRemover);
    }

    protected void limparOperacaoCRUD(AnchorPane telaOperacional) {
        AnchorPane[] telasOperacionais = new AnchorPane[]{telaInserir, telaAtualizar, telaBuscar, telaRemover};
        modoAtualizacao = "";
        modoRemocao = "";
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
                        Label label = ((Label) node);
                        if (label.getId() != null && !label.getId().equals("titulo"))
                            label.setVisible(false);
                    }
                }
            }
        telaOperacional.setVisible(true);
    }

    protected void limparTelaCRUD() {
        AnchorPane[] telasOperacionais = new AnchorPane[]{telaInserir, telaAtualizar, telaBuscar, telaRemover};
        for (AnchorPane tela : telasOperacionais)
            if (tela.isVisible()) {
                tela.setVisible(false);
                for (Node node : tela.getChildren()) {
                    if (node instanceof TextInputControl) {
                        ((TextInputControl) node).setText(null);
                    } else if (node instanceof MenuButton) {
                        ((MenuButton) node).setText("Selecionar Genero");
                    } else if (node instanceof DatePicker) {
                        ((DatePicker) node).setValue(null);
                    } else if (node instanceof Label) {
                        Label label = ((Label) node);
                        if (label.getId() != null && !label.getId().equals("titulo"))
                            label.setVisible(false);
                    }
                }
            }
    }

    public boolean preencheuEntradasInsercao() {
        for (Node node : telaInserir.getChildren()) {
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                if (tf.getText().trim().isEmpty()) {
                    return false;
                }
            } else if (node instanceof DatePicker) {
                DatePicker dp = (DatePicker) node;
                if (dp.getValue() == null) {
                    return false;
                }
            } else if (node instanceof MenuButton) {
                MenuButton mb = (MenuButton) node;
                if (mb.getText().equals("Selecionar Genero")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean preencheuAlgumaEntradaAtualizacao() {
        if (!CampoTrocaGenero.getText().trim().isEmpty())
            return true;
        if (!CampoTrocaGenero.getText().equals("Selecionar Genero"))
            return true;
        if (!CampoTrocaImage.getText().trim().isEmpty())
            return true;
        if (!CampoTrocaPrice.getText().trim().isEmpty())
            return true;
        if (CampoTrocaReleaseDate.getValue() != null)
            return true;
        if (!CampoTrocaDesenvolvedora.getText().trim().isEmpty())
            return true;
        if (!CampoTrocaPublicadora.getText().trim().isEmpty())
            return true;
        if (!CampoTrocaVideo.getText().trim().isEmpty())
            return true;
        return false;
    }

    @FXML
    protected void selecionarImagem() {
        ViewController.escolherImagem(urlImage);
    }

    @FXML
    protected void selecionarVideo() {
        escolherVideo(urlVideo);
    }

    @FXML
    protected void selecionarVideoAtualizar() {
        ViewController.escolherVideo(CampoTrocaVideo);
    }

    @FXML
    protected void selecionarImagemAtualizar() {
        ViewController.escolherImagem(CampoTrocaImage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewController.desabilitarDatasFuturas(releaseDate);
        ViewController.desabilitarDatasFuturas(CampoTrocaReleaseDate);
        ViewController.preencheMenuGeneros(genres);
        ViewController.preencheMenuGeneros(CampoBuscarGenero);
        ViewController.preencheMenuGeneros(CampoTrocaGenero);
        campoRemoverId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                campoRemoverId.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        CampoBuscarId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                CampoBuscarId.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        price.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                price.setText(oldValue);
            }
        });
    }

}