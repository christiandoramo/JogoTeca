package br.jogoteca.system.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.data.IGenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.models.Game;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Genre;

import br.jogoteca.system.models.Usuario;

public class PedidoController {
	
	
	private IGenericRepository<Usuario> pedidoRepositorio;
	private int lastId = 1;
	
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
	
	
	public void adicionarPedido(String id,String pedido) {
		Usuario usuario = new Usuario(id,pedido);
		try {
			pedidoRepositorio.insert(usuario);
		} catch (ElementAlreadyExistsException e) {
			System.out.println("erro");
		}
	}
	
	
	public Usuario buscarPeloId(String id) throws ElementDoesNotExistException{
			return pedidoRepositorio.read().stream().filter(usuario -> usuario.getId().equals(id)).findFirst().orElse(null);
		
	}
	public Usuario buscarPeloNome(String pedido) throws ElementDoesNotExistException {
		return pedidoRepositorio.read().stream().filter(usuario -> usuario.getPedido().equals(pedido)).findFirst().orElse(null);
	}
	
	public void removerPedidoId(String id) throws ElementDoesNotExistException {
		Usuario usuario = buscarPeloId(id);			
			try {
				pedidoRepositorio.delete(usuario);
				lastId--;
			} catch (Exception e) {
				System.out.println("erro");
			}
		}
	
	public void updatePedidoId(String id, String pedido) throws ElementDoesNotExistException{			
		Usuario pedido1 = buscarPeloId(id);
		if (pedido1 != null) {
			if (id != null && !id.equals(""))
				pedido1.setId(id);
			if (pedido != null && !pedido.equals(""))
				pedido1.setId(pedido);					
			pedidoRepositorio.update(pedido1);
			}
		}
	
	
	
	
	
	}	
