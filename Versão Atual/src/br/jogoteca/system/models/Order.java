package br.jogoteca.system.models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private int id;
	private LocalDateTime moment;
	private User user;
	private List<GameItem> items;
	private OrderStatus status;
	
	

	public Double totalValue() {
		return items.stream().mapToDouble(x -> x.getValue()).sum();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GameItem> getItems() {
		return items;
	}

	public void setItems(List<GameItem> items) {
		this.items = items;
	}

	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Order(int id, LocalDateTime moment, User user, List<GameItem> items, OrderStatus status) {
		super();
		this.id = id;
		this.moment = moment;
		this.user = user;
		this.items = items;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
