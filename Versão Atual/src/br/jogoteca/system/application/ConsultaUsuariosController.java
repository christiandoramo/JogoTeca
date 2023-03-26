package br.jogoteca.system.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.jogoteca.system.controllers.UserController;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;

public class ConsultaUsuariosController implements Initializable {
	@FXML
	private AnchorPane telaConsultaUsuario;
	@FXML
	protected TextField campoBuscarId;
	@FXML
	protected TextField campoBuscarCpf;
	@FXML
	protected TextField campoBuscarNome;
	@FXML
	protected ListView<User> userListView;
	@FXML
	protected Label logBuscas;

	UserController uc = UserController.getInstance();

	@FXML
	protected void voltarParaPrincipalAdmin(ActionEvent event) {
		limparTelaConsultaClientes();
		ViewsController.changeScreen(Tela.PRINCIPALADMIN);
	}

	protected void limparTelaConsultaClientes() {
		for (Node node : telaConsultaUsuario.getChildren()) {
			if (node instanceof TextInputControl) {
				((TextInputControl) node).setText(null);
			}
		}
	}

	@FXML
	protected void searchPorId() {
		int id = Integer.parseInt(campoBuscarId.getText());
		List<User> usersAchados = new ArrayList<>();
		if (id > 0) {
			try {
				User n = uc.searchUserById2(id);
				if (n != null) {
					usersAchados.add(n);
					ViewsController.mostraUsuariosAchados(userListView, usersAchados);
					logBuscas.setVisible(false);
					System.out.println(n.getCPF());
				} else {
					System.out.println("id nao achado");
					throw new ElementDoesNotExistException(n);
				}
			} catch (Exception e) {
			    if (e instanceof ElementDoesNotExistException) {
			        logBuscas.setText("Erro: Usuario não encontrado");
			    } else {
			        logBuscas.setText("Ocorreu um erro. Tente novamente.");
			    }
				logBuscas.setVisible(true);
			}
		} else {
			logBuscas.setText("Erro: Digite um ID válido");
			logBuscas.setVisible(true);
		}
	}

	@FXML
	protected void searchPorCpf(ActionEvent event) {
		String cpf = campoBuscarCpf.getText();
		List<User> usersAchados = new ArrayList<>();
		if (cpf != null && !cpf.equals("")) {
			try {
				User n = uc.searchUserByCPF2(campoBuscarCpf.getText());
				if (n != null) {
					usersAchados.add(n);
					ViewsController.mostraUsuariosAchados(userListView, usersAchados);
					logBuscas.setVisible(false);
					System.out.println(n.getCPF());
				} else
					throw new ElementDoesNotExistException(n);
			} catch (Exception e) {
				logBuscas.setText("ERRROO");
				logBuscas.setVisible(true);
			    if (e instanceof ElementDoesNotExistException) {
			        logBuscas.setText("Erro: Usuario não encontrado");
			    } else {
			        logBuscas.setText("Ocorreu um erro. Tente novamente.");
			    }
			}
		} else {
			logBuscas.setText("Erro: Digite um CPF válido");
			logBuscas.setVisible(true);
		}

	}

	@FXML
	protected void searchPorNome(ActionEvent event) {
		String nome = campoBuscarNome.getText();
		List<User> usersAchados = new ArrayList<>();
		if (nome != null && !nome.equals("")) {
			try {
				User n = uc.searchUserByName2(nome);
				if (n != null) {
					usersAchados.add(n);
					ViewsController.mostraUsuariosAchados(userListView, usersAchados);
					logBuscas.setVisible(false);
					System.out.println(n.getCPF());
				} else
					throw new ElementDoesNotExistException(n);
			} catch (Exception e) {
			    if (e instanceof ElementDoesNotExistException) {
			        logBuscas.setText("Erro: Usuario não encontrado");
			    } else {
			        logBuscas.setText("Ocorreu um erro. Tente novamente.");
			    }
				logBuscas.setVisible(true);
			}
		} else {
			logBuscas.setText("Erro: Digite um Nome válido");
			logBuscas.setVisible(true);
		}
	}

	@FXML
	protected void searchTodos() {
		try {
			List<User> allUsers = uc.searchAllUsers2();
			if (!allUsers.isEmpty()) {
				ViewsController.mostraUsuariosAchados(userListView, allUsers);
				logBuscas.setVisible(false);
			} else
				throw new ElementsDoNotExistException(allUsers);
		} catch (Exception e) {
		    if (e instanceof ElementsDoNotExistException) {
		        logBuscas.setText("Erro: Nenhum usuário encontrado");
		    } else {
		        logBuscas.setText("Ocorreu um erro. Tente novamente.");
		    }
			logBuscas.setVisible(true);
		}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ViewsController.controlaInteiro(campoBuscarId);
	}

}
