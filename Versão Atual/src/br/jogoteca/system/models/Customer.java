package br.jogoteca.system.models;

import java.util.List;

public class Customer {
	private int id;
	private String CPF;
	private String login;
	private String password;
	private String name;
	private String address;
	private String phones;
	private List<Game> wishList;	
	private List<Game> favoriteList;
}
