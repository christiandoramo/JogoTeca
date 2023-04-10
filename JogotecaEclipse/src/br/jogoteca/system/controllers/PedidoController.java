package br.jogoteca.system.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.data.IGenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.User;
import br.jogoteca.system.models.Metodo;

public class PedidoController {

	private IGenericRepository<Pedido> pedidoRepositorio;
	private int lastId;

	private static PedidoController instance;

	private PedidoController() {
		this.pedidoRepositorio = new GenericRepository<>("Pedidos.dat");
		lastId = pedidoRepositorio.read().size();
		System.out.println(lastId);
	}

	public static PedidoController getInstance() {
		if (instance == null) {
			instance = new PedidoController();
		}
		return instance;
	}

	public void adicionarPedido(LocalDateTime momento, List<GameItem> itens, User usuario,Metodo metodo)
			throws ElementAlreadyExistsException {
		Pedido pedidos = new Pedido(lastId + 1, momento, itens, usuario,metodo);
		pedidoRepositorio.insert(pedidos);
		lastId++;
	}
	
	public List<Pedido> buscarTodos() throws ElementsDoNotExistException {
		return pedidoRepositorio.read();
	}

	public Pedido buscarPeloId(int id) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getId() == id).findFirst().orElse(null);
	}

	public Pedido buscarPeloUser(User usuario) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getUser().equals(usuario)).findFirst()
				.orElse(null);
	}

	public Pedido buscarPeloGameItem(List<GameItem> itens) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getItens().equals(itens)).findFirst()
				.orElse(null);
	}

	public Pedido buscarPeloMomento(LocalDateTime momento) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getMomento().equals(momento)).findFirst()
				.orElse(null);
	}

	public void removerPedidoId(int id) throws ElementDoesNotExistException {
		Pedido pedido = buscarPeloId(id);
		pedidoRepositorio.delete(pedido);
		lastId--;
	}

	public void updatePedidoById(int id, LocalDateTime momento, List<GameItem> itens, User usuario,Metodo metodo)
			throws ElementDoesNotExistException, ElementWithSameNameExistsException {
		Pedido pedido = buscarPeloId(id);
		if (pedido != null) {
			if (itens != null)
				pedido.setItens(itens);
			if (usuario != null)
				pedido.setUser(usuario);
			if (momento != null)
				pedido.setMomento(momento);
			if (metodo != null)
				pedido.setMetodo(metodo);
			pedidoRepositorio.update(pedido);
		}
	}

	public void destroyPedidoPeloId(int id) throws ElementDoesNotExistException {
		Pedido pedido = buscarPeloId(id);
		pedidoRepositorio.delete(pedido);
		lastId--;
	}


    public boolean checaSeUmJogoJaFoiComprado(User user, GameItem novoItem) {
        List<GameItem> itemsDoUsuario = pedidoRepositorio.read()
                .stream()
                .filter(pedido -> pedido.getUser().equals(user))
                .flatMap(pedido -> pedido.getItens().stream())
                .collect(Collectors.toList());
        List<Game> jogosDoUsuario = itemsDoUsuario.stream()
                .map(item -> item.getGame()).collect(Collectors.toList());
        return jogosDoUsuario.contains(novoItem);
    }
	
	
}
