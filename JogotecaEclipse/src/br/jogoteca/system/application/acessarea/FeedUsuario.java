package br.jogoteca.system.application.acessarea;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import br.jogoteca.system.controllers.GameItemControllers;
import br.jogoteca.system.controllers.GamesController;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FeedUsuario extends AcessAreaController implements Initializable {
    @FXML
    protected Label readLog;
    @FXML
    protected TextField campoBuscarNome;
    @FXML
    protected MenuButton campoBuscarGenero;
    @FXML
    protected ListView<GameItem> listaDeJogos;

//    @FXML
//    void clickouEmVerJogo(ActionEvent event) throws IOException{
//        setJogoAtual();


    //    }
    void irAoPerfilDoJogoSelecionado(ActionEvent event) throws IOException {
      	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
		irParaPerfilDoJogo(event);
    }

    @FXML
    void deslogar(ActionEvent event) throws IOException {
      	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        usuarioAtual = null;
        irParaLogin(event);
    }

    @FXML
    void irParaWishList(ActionEvent event) throws IOException {
      	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaWishlist(event);
        jogoAtual = null;
    }

    @FXML
    void irParaPerfilUsuario(ActionEvent event) throws IOException {
      	setStage((Stage) ((Node) event.getSource()).getScene().getWindow());
        irParaPerfilDoUsuario(event);
    }
    
	@FXML
	protected void listarTodos() {
		GameItemControllers gic = GameItemControllers.getInstance();
		try {
			List<GameItem> allGameItem = gic.searchAllGameItem();
			if (!allGameItem.isEmpty()) {
				AcessAreaController.mostraGamesItensAchados(listaDeJogos, allGameItem);
				readLog.setVisible(false);
			} else
				throw new ElementsDoNotExistException(allGameItem);
		} catch (Exception e) {
			if (e instanceof ElementsDoNotExistException) {
				readLog.setText("Erro: Nenhum jogo encontrado");
			} else {
				readLog.setText("Ocorreu um erro. Tente novamente.");
			}
			readLog.setVisible(true);

		}
	}
   

    @FXML
    protected void  buscarPorGenero() {
        Genre genero = (Genre) campoBuscarGenero.getUserData();
        GameItemControllers gic = GameItemControllers.getInstance();
        try {
            List<GameItem> gamesAchados = gic.searchAllGameItem().stream().filter(x-> x.getGame().getGenre().equals(genero)).collect(Collectors.toList());
            if (!gamesAchados.isEmpty()) {
//                Comparator<GameItem> nameComparator = Comparator.comparing(GameItem::getGame);
//                gamesAchados.sort(nameComparator);
                AcessAreaController.mostraGamesItensAchados(listaDeJogos, gamesAchados);
                readLog.setVisible(false);
            } else
                throw new ElementsDoNotExistException(gamesAchados);
        } catch (Exception e) {
            if (e instanceof ElementsDoNotExistException) {
                readLog.setText("Erro: Nenhum Jogo encontrado");
            } else {
                readLog.setText("Ocorreu um erro. Tente novamente.");
            }
            readLog.setVisible(true);
        }
    }
    
    @FXML
    protected void buscarPorNome() {
        GameItemControllers gic = GameItemControllers.getInstance();
        GamesController gc = GamesController.getInstance();
        AcessAreaController.searchGameItemByNome(gc,gic, campoBuscarNome, listaDeJogos, readLog);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AcessAreaController.preencheMenuGeneros(campoBuscarGenero);
        listarTodos();
        // carrega a função do clique na imagem para ir para o perfil
        listaDeJogos.setOnMouseClicked(event -> {
            try {
                itemAtual = listaDeJogos.getSelectionModel().getSelectedItem();
                jogoAtual = itemAtual.getGame();
                irAoPerfilDoJogoSelecionado(new ActionEvent());
            } catch (IOException e) {
               // e.printStackTrace();
            }
        });
    }
}
