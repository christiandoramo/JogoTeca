package br.jogoteca.system.controllers;

import java.time.LocalDateTime;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Order;
import br.jogoteca.system.models.User;

public class OrdersController {
	private GenericRepository<Order> ordersRepository;
	private int lastId = 1;

	private static OrdersController instance;

	private OrdersController() {
		this.ordersRepository = new GenericRepository<>("Ordes.dat");
		lastId = ordersRepository.read().size();
		System.out.println(lastId);
	}

	public static OrdersController getInstance() {
		if (instance == null) {
			instance = new OrdersController();
		}
		return instance;
	}

	// public boolean contemNome(String nome) {
	// boolean contemNome = ordersRepository.read().stream().anyMatch(item ->
	// item.getName().equals(nome));
	// return contemNome;
	// }

	// public void mostrarordersRepository() {
	// if (!ordersRepository.read().isEmpty())
	// for (Order order : ordersRepository.read())
	// System.out.println(order.getId());
	// }

	public void insertOrder(int id, LocalDateTime moment, User user, GameItem item) {

		Order order = new Order(lastId + 1, moment, user, item);
		try {
			ordersRepository.insert(order);
			lastId++;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Order searchOrderById(int id) {
		return ordersRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);

	}

	public List<Order> searchAllOrders() {
		return ordersRepository.read();
	}

	// trocar os itens do pedido - no caso um pedido errado
	// função ainda não foi bem trabalhada quando haver gui
	public void updateOrderById(int id, GameItem item) {
		Order order = searchOrderById(id);
		if (order != null && item != null) {
			order.setitem(item);
			try {
				ordersRepository.update(order);
				System.out.println("Sucesso: O jogo foi atualizado");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			// futuramente será uma exceção
			System.out.println("Erro: pedido não encontrado");
		}
	}

	public void destroyOrdeById(int id) {
		Order Order = searchOrderById(id);
		try {
			ordersRepository.delete(Order);
			lastId--;
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}