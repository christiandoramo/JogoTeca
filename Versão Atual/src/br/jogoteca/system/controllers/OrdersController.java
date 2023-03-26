package br.jogoteca.system.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.GameItem;
import br.jogoteca.system.models.Order;
import br.jogoteca.system.models.OrderStatus;
import br.jogoteca.system.models.User;

public class OrdersController {
	private GenericRepository<Order> ordersRepository;
	private int lastId;

	private static OrdersController instance;

	private OrdersController() {
		this.ordersRepository = new GenericRepository<>("Orders.dat");
		lastId = ordersRepository.read().size();
	}

	public static OrdersController getInstance() {
		if (instance == null) {
			instance = new OrdersController();
		}
		return instance;
	}

	public void insertOrder(LocalDateTime moment, User user, List<GameItem> items, OrderStatus status)
			throws ElementAlreadyExistsException {
		// status para PEDIDO instanciado no carrinho - ESPERANDO_PAGAMENTO
		// status para PEDIDO COMPRADO no carrinho - PAGO
		// status para PEDIDO LIMPADO no carrinho - CANCELADO
		// status para PEDIDO após uma compra direta - PAGO
		Order order = new Order(lastId + 1, moment, user, items, status);
		ordersRepository.insert(order);
		lastId++;
	}

	public Order searchOrderById(int id) throws ElementDoesNotExistException {
		return ordersRepository.read().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public List<Order> searchAllOrders() throws ElementsDoNotExistException {
		return ordersRepository.read();
	}

	public List<Order> searchAllUserOrders(User user) throws ElementsDoNotExistException {
		List<Order> userOrders = ordersRepository.read().stream().filter(order -> order.getUser().equals(user))
				.collect(Collectors.toList());
		return userOrders;
	}

	public Order searchCurrentUserOrder(User user) throws ElementsDoNotExistException {
		List<Order> userOrders = searchAllUserOrders(user);
		return userOrders.get(userOrders.size() - 1);
	}

	// PARA O CARRINNHO
	public Order searchCurrentCarrinho(User user) throws ElementsDoNotExistException {
		List<Order> userOrders = searchAllUserOrders(user);
		// apenas pedidos que são carrinhos
		List<Order> carrinhos = userOrders.stream().filter(x -> x.getStatus() == OrderStatus.ESPERANDOPAGAMENTO)
				.collect(Collectors.toList());
		// ultimo carrinho com status ESPERANDOPAGAMENTO
		// todo carrinho antes do ultimo será CANCELADO ou PAGO
		return carrinhos.get(carrinhos.size() - 1);
	}

	public List<GameItem> searchAllUserOrdersItems(User user) throws ElementsDoNotExistException {
		List<Order> allUserOrders = searchAllUserOrders(user);
		return allUserOrders.stream().flatMap(order -> order.getItems().stream()).collect(Collectors.toList());
	}

	// PARA O CARRINNHO
	public List<GameItem> searchCurrentCarrinhoItems(User user) throws ElementsDoNotExistException {
		return searchCurrentCarrinho(user).getItems();
	}

	// trocar os itens do pedido - no caso um pedido errado
	// função ainda não foi bem trabalhada quando haver gui
	public void updateOrderById(int id, List<GameItem> items) throws ElementDoesNotExistException {
		Order order = searchOrderById(id);
		order.setItems(items);
		ordersRepository.update(order);
	}

	public void destroyOrderById(int id) throws ElementDoesNotExistException {
		Order Order = searchOrderById(id);
		ordersRepository.delete(Order);
		lastId--;
	}

	public void destroyCurrentUserOrder(int id) throws ElementDoesNotExistException {
		Order Order = searchOrderById(id);
		ordersRepository.delete(Order);
		lastId--;
	}
}