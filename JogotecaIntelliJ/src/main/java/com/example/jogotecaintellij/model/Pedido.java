package com.example.jogotecaintellij.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
	
	
	private int id;
	private LocalDateTime momento;
	private List<GameItem> itens;
	private User user;
	
	public Pedido(int id, LocalDateTime momento, List<GameItem> itens, User user) {
		super();
		this.id = id;
		this.momento = momento;
		this.itens = itens;
		this.user = user;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getMomento() {
		return momento;
	}
	public void setMomento(LocalDateTime momento) {
		this.momento = momento;
	}
	public List<GameItem> getItens() {
		return itens;
	}
	public void setItens(List<GameItem> itens) {
		this.itens = itens;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
					

}
