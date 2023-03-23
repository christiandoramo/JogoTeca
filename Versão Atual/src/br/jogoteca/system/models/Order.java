package br.jogoteca.system.models;

import java.time.LocalDateTime;

public class Order {
	private int id;
	private LocalDateTime moment;
	private User user;
	private GameItem item;

	public Double totalValue() {
		return item.getGames().stream().mapToDouble(x -> x.getPrice()).sum();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getmoment() {
		return moment;
	}

	public void setmoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GameItem getitem() {
		return item;
	}

	public void setitem(GameItem item) {
		this.item = item;
	}

	public Order(int id, LocalDateTime moment, User user, GameItem item) {
		super();
		this.id = id;
		this.moment = moment;
		this.user = user;
		this.item = item;
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
