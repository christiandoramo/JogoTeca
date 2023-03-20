package br.jogoteca.system.models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private int ID;
	private List<GameItem> gameOrder;
	private OrderStatus orderStatus;
	private LocalDateTime moment;
	
	public Double totalValue() {
		return 0.0;
	}
}
