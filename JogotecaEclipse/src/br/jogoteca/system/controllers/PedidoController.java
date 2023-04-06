package br.jogoteca.system.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.data.IGenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Order;
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.User;

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

	public void adicionarPedido(LocalDateTime momento, List<GameItem> itens, User usuario)
			throws ElementAlreadyExistsException {
		Pedido pedidos = new Pedido(lastId + 1, momento, itens, usuario);
		pedidoRepositorio.insert(pedidos);
		lastId++;
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

	public void updatePedidoById(int id, LocalDateTime momento, List<GameItem> itens, User usuario)
			throws ElementDoesNotExistException, ElementWithSameNameExistsException {
		Pedido pedido = buscarPeloId(id);
		if (pedido != null) {
			if (itens != null)
				pedido.setItens(itens);
			if (usuario != null)
				pedido.setUser(usuario);
			if (momento != null)
				pedido.setMomento(momento);
			pedidoRepositorio.update(pedido);
		}
	}

	public void destroyPedidoPeloId(int id) throws ElementDoesNotExistException {
		Pedido pedido = buscarPeloId(id);
		pedidoRepositorio.delete(pedido);
		lastId--;
	}
	/*
	 * public void destroyCurrentUserOrder(int id) throws
	 * ElementDoesNotExistException { Order Order = buscarPeloId(id);
	 * pedidoRepositorio.delete(pedido); lastId--; }
	 */
}
