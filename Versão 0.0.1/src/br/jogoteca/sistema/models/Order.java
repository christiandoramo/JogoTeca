package br.jogoteca.sistema.models;

import java.time.LocalDateTime;

public class Order {
	private int ID;
	private GameOrder gameOrder;
	private OrderStatus orderStatus;
	private LocalDateTime moment;
	
	public Double totalValue() {
		return 0.0;
	}
}
