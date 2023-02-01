package projeto2va;

import java.util.List;
import java.util.Scanner;

// Sistema de manutenção dos games com acesso ao sistema de registros
public class SystemManager {
	Scanner sc = new Scanner(System.in);
	RegisterManager rm = new RegisterManager();
	private User usuario;
	private int usuariosLogados = 0;

	private void cB() {
		// Limpar buffer de teclado
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
	}

	public void iniciarSessao() {
		System.out.println("Inicio de sessão");
		System.out.print("Nome do usuario: ");
		usuario = new User(++usuariosLogados, sc.nextLine());
	}

	public void opcoesPrincipais() {
		boolean rodar = true;
		while (rodar) {
			System.out.println("1: vender");
			System.out.println("2: alugar");
			System.out.println("3: receber alugado");
			System.out.println("4: nova sessão de usuario");
			System.out.println("5: manutenção dos games");
			System.out.println("6: encerrar Sistema");
			System.out.print("Insira o numero da opção: ");
			int opcao = sc.nextInt();
			cB();
			if (opcao == 1) {
				System.out.print("Insira o nome ou id do produto que deseja vender: ");
				String entrada = sc.nextLine();

				System.out.print("Insira a quantidade: ");
				int qte = sc.nextInt();
				cB();
				vender(entrada, usuario, qte);
			} else if (opcao == 2) {
				System.out.print("Insira o nome ou id do produto que deseja alugar: ");
				String entrada = sc.nextLine();
				System.out.print("Insira a quantidade: ");
				int qte = sc.nextInt();
				cB();
				emprestar(entrada, usuario, qte);
			} else if (opcao == 3) {
				System.out.print("Insira o nome ou id do produto que deseja devolver: ");
				String entrada = sc.nextLine().toString();
				System.out.print("Insira a quantidade: ");
				int qte = sc.nextInt();
				cB();
				receber(entrada, usuario, qte);
			} else if (opcao == 4) {
				iniciarSessao();
			} else if (opcao == 5) {
				opcoesDeManutencao();
			} else if (opcao == 6) {
				sc.close();
				rodar = false;
			}
		}
	}

	public void opcoesDeManutencao() {
		boolean rodar = true;
		while (rodar) {
			System.out.println("1: adicionar ou Atualizar um game a venda");
			System.out.println("2: adicionar ou Atualizar um game para alugar");
			System.out.println("3: mostrar games Disponiveis");
			System.out.println("4: mostrar todo os registros");
			System.out.println("5: buscar por um game");
			System.out.println("6: mostar games a venda");
			System.out.println("7: mostrar games para alugar");
			System.out.println("8: mudar nome de um game a venda");
			System.out.println("9: mudar nome de um game para alugar");
			System.out.println("10: mudar preço de um game a venda");
			System.out.println("11: mudar preço de um game para alugar");
			System.out.println("12: remover um game para alugar");
			System.out.println("13: remover um game a venda");
			System.out.println("14: encerrar Manutenção");
			System.out.print("Insira o numero da opção: ");
			int opcao = sc.nextInt();
			cB();
			// CREATE
			if (opcao == 1) {
				System.out.print("Registre o nome do game para venda: ");
				String nome = sc.nextLine();
				List<RegistroGame> regs = rm.allGames.stream()
						.filter(x -> x.getGame().getEstado().equals(Estado.VendaDisponivel)
								&& x.getGame().getNome().equals(nome))
						.toList();
				RegistroGame gameX = !regs.isEmpty() ? regs.get(0) : null;
				System.out.print("Insira a quantidade: ");
				int qte = sc.nextInt();
				cB();
				if (gameX == null) {
					System.out.print("Insira o genero do jogo: ");
					Genero genero = retornarGenero(sc.nextLine());
					System.out.print("Insira o ano de lançamento: ");
					String ano = sc.nextLine();
					System.out.print("Insira a produtora: ");
					String produtora = sc.nextLine();
					System.out.print("Insira a publicadora: ");
					String publicadora = sc.nextLine();
					System.out.print("Insira o preço do produto: ");
					float preco = Float.parseFloat(sc.nextLine());
					for (int i = 0; i < qte; i++) {
						Game game = new Game("" + (rm.allGames.size() + 1), nome, produtora, ano, publicadora, genero,
								Estado.VendaDisponivel);
						RegistroGame rv = new RegistroGame(rm.allGames.size() + 1, game, preco);
						rm.addRegistroVenda(rv);
					}
					System.out.println(qte + " unidade(s) do jogo " + nome + " inserida(s) com sucesso");
				}
				// caso já exista apeas vai adicionar o registro com os mesmos dados do
				// registrado gameX
				else {
					for (int i = 0; i < qte; i++) {
						Game game = new Game("" + (rm.allGames.size() + 1), gameX.getGame().getNome(),
								gameX.getGame().getProdutora(), gameX.getGame().getAno(),
								gameX.getGame().getPublicadora(), gameX.getGame().getGenero(), Estado.VendaDisponivel);
						RegistroGame rv = new RegistroGame(rm.allGames.size(), game, gameX.getPreco());
						rm.addRegistroVenda(rv);
					}
					System.out.println(qte + " unidade(s) do jogo " + nome + " inserida(s) com sucesso");
				}
			} else if (opcao == 2) {
				System.out.print("Registre o nome do game alugavel: ");
				String nome = sc.nextLine();
				cB();
				List<RegistroGame> regs = rm.allGames.stream()
						.filter(x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel)
								&& x.getGame().getNome().equals(nome))
						.toList();
				RegistroGame gameX = !regs.isEmpty() ? regs.get(0) : null;
				System.out.print("Insira a quantidade: ");
				int qte = sc.nextInt();
				cB();
				if (gameX == null) {
					System.out.print("Insira o genero do jogo: ");
					Genero genero = retornarGenero(sc.nextLine());
					cB();
					System.out.print("Insira o ano de lançamento: ");
					String ano = sc.nextLine();
					cB();
					System.out.print("Insira a produtora: ");
					String produtora = sc.nextLine();
					cB();
					System.out.print("Insira a publicadora: ");
					String publicadora = sc.nextLine();
					cB();
					System.out.print("Insira o preço do produto: ");
					float preco = Float.parseFloat(sc.nextLine());
					for (int i = 0; i < qte; i++) {
						Game game = new Game("" + (rm.allGames.size() + 1), nome, produtora, ano, publicadora, genero,
								Estado.AluguelDisponivel);
						RegistroGame rv = new RegistroGame(rm.allGames.size() + 1, game, preco);
						rm.addRegistroVenda(rv);
					}
					System.out.println(qte + " unidade(s) do jogo " + nome + " inserida(s) com sucesso");
				} else {
					for (int i = 0; i < qte; i++) {
						Game game = new Game("" + (rm.allGames.size() + 1), gameX.getGame().getNome(),
								gameX.getGame().getProdutora(), gameX.getGame().getAno(),
								gameX.getGame().getPublicadora(), gameX.getGame().getGenero(),
								Estado.AluguelDisponivel);
						RegistroGame rv = new RegistroGame(rm.allGames.size() + 1, game, gameX.getPreco());
						rm.addRegistroVenda(rv);
					}
					System.out.println(qte + " unidade(s) do jogo " + nome + " inserida(s) com sucesso");
				}

			}
			// READ
			if (opcao == 3) {
				rm.mostrarGamesDisponiveis();
			} else if (opcao == 4) {
				rm.mostrarTodoOsRegistros();
			} else if (opcao == 5) {
				System.out.print("Procure pelo nome do jogo: ");
				rm.buscarUmGame(sc.nextLine());
			} else if (opcao == 6) {
				rm.mostarGamesAVenda();
			} else if (opcao == 7) {
				rm.mostrarGamesAlugaveis();
			}
			// UPDATE
			else if (opcao == 8) {
				System.out.print("Nome antigo do jogo: ");
				String antigo = sc.nextLine();
				System.out.print("Novo nome do jogo: ");
				rm.atualizarNomedeUmAVenda(antigo, sc.nextLine());
			} else if (opcao == 9) {
				System.out.print("Nome antigo do jogo: ");
				String antigo = sc.nextLine();
				System.out.print("Novo nome do jogo: ");
				rm.atualizarNomedeUmAlugavel(antigo, sc.nextLine());
			} else if (opcao == 10) {
				System.out.print("Nome do jogo: ");
				String antigo = sc.nextLine();
				System.out.print("Novo preço do jogo: ");
				float novoPreco = Float.parseFloat(sc.nextLine());
				rm.atualizarPrecodeUmAVenda(antigo, novoPreco);
			} else if (opcao == 11) {
				System.out.print("Nome do jogo: ");
				String antigo = sc.nextLine();
				System.out.print("Novo preço do jogo: ");
				float novoPreco = Float.parseFloat(sc.nextLine());
				rm.atualizarPrecodeUmAlugavel(antigo, novoPreco);
			}
			// DELETE
			else if (opcao == 12) {
				System.out.print("Nome do jogo: ");
				rm.removerUmGameAlugavel(sc.nextLine());
			} else if (opcao == 13) {
				System.out.print("Nome do jogo: ");
				rm.removerUmGameAVenda(sc.nextLine());
			}
			// fim das manutenções
			else if (opcao == 14) {
				rodar = false;
			}

		}
	}

	public Genero retornarGenero(String generoString) {
		if (generoString.toUpperCase().equals("ACTION")) {
			return Genero.ACTION;
		} else if (generoString.toUpperCase().equals("AVENTURA")) {
			return Genero.AVENTURA;
		} else if (generoString.toUpperCase().equals("SHOOTER")) {
			return Genero.SHOOTER;
		} else if (generoString.toUpperCase().equals("RACING")) {
			return Genero.RACING;
		} else if (generoString.toUpperCase().equals("SPORT")) {
			return Genero.SPORT;
		} else if (generoString.toUpperCase().equals("RPG")) {
			return Genero.RPG;
		} else if (generoString.toUpperCase().equals("STRATEGY")) {
			return Genero.STRATEGY;
		} else if (generoString.toUpperCase().equals("ARCADE")) {
			return Genero.ARCADE;
		} else if (generoString.toUpperCase().equals("FIGHTING")) {
			return Genero.FIGHTING;
		}
		return Genero.DESCONHECIDO;
	}

	// SISTEMA VENDA
	// ###############################################################################

	public void vender(String entrada, User usuario, int qte) {
		List<RegistroGame> gameVendiveis = rm.allGames.stream().filter(
				x -> x.getGame().getEstado().equals(Estado.VendaDisponivel) && x.getGame().getNome().equals(entrada))
				.toList();
		if (gameVendiveis.isEmpty()) {
			gameVendiveis = rm.allGames.stream().filter(x -> x.getGame().getEstado().equals(Estado.VendaDisponivel)
					&& x.getGame().getCodigo().equals(entrada)).toList();
		}
		if (gameVendiveis.isEmpty()) {
			System.out.println("Erro: Game não encontrado");
			return;
		}
		if (qte < gameVendiveis.size()) {
			float receita = qte * gameVendiveis.get(0).getPreco();
			String titulo = gameVendiveis.get(0).getGame().getNome();
			for (int i = 0; i < qte; i++) {
				gameVendiveis.get(i);
				gameVendiveis.get(i).getGame().setReceita(receita);
				gameVendiveis.get(i).getGame().setEstado(Estado.Vendido);
				gameVendiveis.get(i).setUsuario(usuario);
			}
			System.out.println(qte + " unidade(s) do jogo " + titulo + " vendida(s) com sucesso");
		} else {
			System.out.println("Erro: quantidade acima do armazenado");
		}
	}

	// SISTEMA ALUGUEL - EMPRESTAR E RECEBER
	// ###############################################################################
	public void emprestar(String entrada, User usuario, int qte) {
		List<RegistroGame> gameAlugaveis = rm.allGames.stream().filter(
				x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel) && x.getGame().getNome().equals(entrada))
				.toList();
		if (gameAlugaveis.isEmpty()) {
			gameAlugaveis = rm.allGames.stream().filter(x -> x.getGame().getEstado().equals(Estado.AluguelDisponivel)
					&& x.getGame().getCodigo().equals(entrada)).toList();
		}
		if (gameAlugaveis.isEmpty()) {
			System.out.println("Erro: Game não encontrado");
			return;
		}
		if (qte < gameAlugaveis.size()) {
			float receita = qte * gameAlugaveis.get(0).getPreco();
			String titulo = gameAlugaveis.get(0).getGame().getNome();
			for (int i = 0; i < qte; i++) {
				gameAlugaveis.get(i);
				gameAlugaveis.get(i).getGame().setReceita(receita);
				gameAlugaveis.get(i).getGame().setEstado(Estado.Alugado);
				gameAlugaveis.get(i).setUsuario(usuario);
			}
			System.out.println(qte + " unidade(s) do jogo " + titulo + " alugada(s) com sucesso");
		} else {
			System.out.println("Erro: quantidade acima do armazenado");
		}
	}

	public void receber(String entrada, User usuario, int qte) {
		List<RegistroGame> gameAlugados = rm.allGames.stream()
				.filter(x -> x.getGame().getEstado().equals(Estado.Alugado) && x.getGame().getNome().equals(entrada))
				.toList();
		if (gameAlugados.isEmpty()) {
			gameAlugados = rm.allGames.stream().filter(
					x -> x.getGame().getEstado().equals(Estado.Alugado) && x.getGame().getCodigo().equals(entrada))
					.toList();
		}
		if (gameAlugados.isEmpty()) {
			System.out.println("Erro: Game não encontrado");
			return;
		}
		if (qte < gameAlugados.size()) {
			String titulo = gameAlugados.get(0).getGame().getNome();
			for (int i = 0; i < qte; i++) {
				gameAlugados.get(i);
				gameAlugados.get(i).getGame().setEstado(Estado.AluguelDisponivel);
				gameAlugados.get(i).setUsuario(null);
			}
			System.out.println(qte + " unidade(s) do jogo " + titulo + " recebida(s) com sucesso");
		} else {
			System.out.println("Erro: quantidade acima do registrado");
		}
	}
}
