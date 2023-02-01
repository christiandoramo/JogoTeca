package projeto2va;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Sistema de Registros de Vendas, Emprestados, e Disponiveis != Games em si
public class RegisterManager {

	public List<RegistroGame> allGames = new ArrayList<>();

	public int qteGameAVenda(String nome) {
		int sum = 0;
		try {
			sum = allGames.stream().filter(
					x -> x.getGame().getEstado().equals(Estado.VendaDisponivel) && x.getGame().getNome().equals(nome))
					.toList().size();
		} catch (Exception e) {
			System.out.println(e);
		}
		return sum;
	}

	public int qteGameAlugavel(String nome) {
		int sum = 0;
		try {
			sum = allGames.stream().filter(
					x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel) && x.getGame().getNome().equals(nome))
					.toList().size();
		} catch (Exception e) {
			System.out.println(e);
		}
		return sum;

	}
	// C - create R - read U - update D - delete

	// CREATE
	// ###############################################################################

	public void addRegistroVenda(RegistroGame rg) {
		rg.getGame().setEstado(Estado.VendaDisponivel);
		allGames.add(rg);
	}

	public void addRegistroAluguel(RegistroGame rg) {
		rg.getGame().setEstado(Estado.AluguelDisponivel);
		allGames.add(rg);

	}

	// READ
	// ###############################################################################

	public void mostrarGamesDisponiveis() {
		mostrarGamesAlugaveis();
		mostarGamesAVenda();
	}

	public void mostrarTodoOsRegistros() {
		List<RegistroGame> alugaveis = allGames.stream()
				.filter(x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel)).toList();
		List<RegistroGame> alugados = allGames.stream().filter(x -> x.getGame().getEstado().equals(Estado.Alugado))
				.toList();
		List<RegistroGame> vendiveis = allGames.stream()
				.filter(x -> x.getGame().getEstado().equals(Estado.VendaDisponivel)).toList();
		List<RegistroGame> vendidos = allGames.stream().filter(x -> x.getGame().getEstado().equals(Estado.Vendido))
				.toList();
		for (RegistroGame b : alugados)
			System.out.println(b + "usuario: " + b.getUsuario() + " receita Total: " + b.getGame().getReceita());
		for (RegistroGame d : vendidos)
			System.out.println(d + " usuario: " + d.getUsuario() + " receita Total: " + d.getGame().getReceita());
		for (RegistroGame c : vendiveis)
			System.out.println(c);
		for (RegistroGame a : alugaveis)
			System.out.println(a);
	}

	public void buscarUmGame(String nome) {
		// buscar um vendivel e um compravel e mostrar a quantidade e status
		List<RegistroGame> gameVendiveis = allGames.stream().filter(
				x -> x.getGame().getEstado().equals(Estado.VendaDisponivel) && x.getGame().getNome().equals(nome))
				.toList();
		List<RegistroGame> gameAlugaveis = (allGames.stream().filter(
				x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel) && x.getGame().getNome().equals(nome))
				.toList());
		int quantidade = gameVendiveis.size();
		if (quantidade > 0)
			System.out.println(gameVendiveis.get(0) + " Quantidade: " + quantidade);
		quantidade = gameAlugaveis.size();
		if (quantidade > 0)
			System.out.println(gameAlugaveis.get(0) + " Quantidade: " + quantidade);
	}

	public void mostarGamesAVenda() {
		List<RegistroGame> gamesCompraveis = allGames.stream()
				.filter(x -> x.getGame().getEstado().equals(Estado.VendaDisponivel)).toList();

		if(!gamesCompraveis.isEmpty()) {
			gamesCompraveis = gamesCompraveis.stream().distinct().toList();
			for (RegistroGame registroGame : gamesCompraveis) {
				int quantidade = qteGameAVenda(registroGame.getGame().getNome());
				System.out.println(registroGame + " Quantidade: " + quantidade);
			}
		}else {
			System.out.println("Sem jogos a venda disponiveis");
		}
	}

	public void mostrarGamesAlugaveis() {
		List<RegistroGame> gamesAlugaveis = allGames.stream()
				.filter(x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel)).toList();
		if(!gamesAlugaveis.isEmpty()) {
			gamesAlugaveis = gamesAlugaveis.stream().distinct().toList();
			for (RegistroGame registroGame : gamesAlugaveis) {
				int quantidade = qteGameAVenda(registroGame.getGame().getNome());
				System.out.println(registroGame + " Quantidade: " + quantidade);
			}
		}
		else {
			System.out.println("Sem jogos alugaveis disponiveis");
		}


	}

	// UPDATE
	// ###############################################################################

	public void atualizarNomedeUmAVenda(String antigo, String nome) {
		List<RegistroGame> gameXs = allGames.stream().filter(x -> x.getGame().getNome().equals(antigo)).toList();
		Consumer<RegistroGame> consumer = x -> x.getGame().setNome(nome);
		if (!gameXs.isEmpty()) {
			gameXs.forEach(consumer);
			System.out.println("nome trocado com sucesso");
		} else {
			System.out.println("Erro: Nome não encontrado");
		}
	}

	public void atualizarNomedeUmAlugavel(String antigo, String nome) {
		List<RegistroGame> gameXs = allGames.stream().filter(x -> x.getGame().getNome().equals(antigo)).toList();
		Consumer<RegistroGame> consumer = x -> x.getGame().setNome(nome);
		if (!gameXs.isEmpty()) {
			gameXs.forEach(consumer);
			System.out.println("nome trocado com sucesso");
		} else {
			System.out.println("Erro: Nome não encontrado");
		}
	}

	public void atualizarPrecodeUmAVenda(String nome, float preco) {
		Consumer<RegistroGame> consumer = x -> x.setPreco(preco);
		allGames.forEach(consumer);
		List<RegistroGame> gameXs = allGames.stream().filter(x -> x.getGame().getNome().equals(nome)).toList();
		if (!gameXs.isEmpty()) {
			gameXs.forEach(consumer);
			System.out.println("preco trocado com sucesso");
		} else {
			System.out.println("Erro: Nome não encontrado");
		}
	}

	public void atualizarPrecodeUmAlugavel(String nome, float preco) {
		Consumer<RegistroGame> consumer = x -> x.setPreco(preco);
		allGames.forEach(consumer);
		List<RegistroGame> gameXs = allGames.stream().filter(x -> x.getGame().getNome().equals(nome)).toList();
		if (!gameXs.isEmpty()) {
			gameXs.forEach(consumer);
			System.out.println("preco trocado com sucesso");
		} else {
			System.out.println("Erro: Nome não encontrado");
		}
	}
	// DELETE
	// ###############################################################################

	public void removerUmGameAlugavel(String nome) {
		List<RegistroGame> listaDeRemovidos = allGames.stream().filter(x -> x.getGame().getNome().equals(nome))
				.toList();
		if (listaDeRemovidos.size() > 0)
			allGames.removeAll(listaDeRemovidos);
		else
			System.out.println("Erro: Jogo não encontrado");

	}

	public void removerUmGameAVenda(String nome) {
		List<RegistroGame> listaDeRemovidos = allGames.stream().filter(x -> x.getGame().getNome().equals(nome))
				.toList();
		if (listaDeRemovidos.size() > 0)
			allGames.removeAll(listaDeRemovidos);
		else
			System.out.println("Erro: Jogo não encontrado");
	}
}
