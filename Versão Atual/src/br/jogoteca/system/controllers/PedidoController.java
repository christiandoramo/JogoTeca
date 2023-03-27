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
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.Usuario;

public class PedidoController {
	
	
	private IGenericRepository<Pedido> pedidoRepositorio;
	private int lastId = 1;
	
	private static PedidoController instance;
	
	private ArrayList<Pedido> pedid;
	
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
	
	
	public void adicionarPedido(LocalDateTime momento, List<GameItem> itens, Usuario usuario) {
		Pedido pedidos = new Pedido(lastId +1,momento,itens,usuario);
		try {
			pedidoRepositorio.insert(pedidos);
			lastId++;			
		} catch (ElementAlreadyExistsException e) {
			System.out.println("erro");
		}
	}
				
	public Pedido buscarPeloId(int id) throws ElementDoesNotExistException{
			return pedidoRepositorio.read().stream().filter(pedido -> pedido.getId() == id).findFirst().orElse(null);		
	}
	
	public Pedido buscarPeloUsuario(Usuario usuario) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getUsuario().equals(usuario)).findFirst().orElse(null);
	}
	
	public Pedido buscarPeloGameItem(List<GameItem> itens) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getItens().equals(itens)).findFirst().orElse(null);
	}		
	
	public Pedido buscarPeloMomento(LocalDateTime momento) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(pedido -> pedido.getMomento().equals(momento)).findFirst().orElse(null);
	}		
	
	public void removerPedidoId(int id) throws ElementDoesNotExistException {
		Pedido pedido = buscarPeloId(id);			
			try {
				pedidoRepositorio.delete(pedido);
				lastId--;
			} catch (Exception e) {
				System.out.println("erro");
			}
		}
	
	public Pedido buscarId(int id) {
		for (Pedido pedido : this.pedid) {
			if (pedido.getId() == id) {
				return pedido;
			}
		}
		return null;
	}
	
	public void updateGameById(int id, LocalDateTime momento, List<GameItem> itens, Usuario usuario)throws ElementDoesNotExistException, ElementWithSameNameExistsException {			 
		Pedido pedido = buscarPeloId(id);
		if (pedido != null) {
			if (itens != null)
				pedido.setItens(itens);			
			if (usuario != null)
				pedido.setUsuario(usuario);
			if (momento != null)
				pedido.setMomento(momento);
			pedidoRepositorio.update(pedido);
		}
	}			
}	
