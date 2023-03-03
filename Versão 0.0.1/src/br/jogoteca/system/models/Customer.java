package br.jogoteca.system.models;

import java.util.List;

public class Customer {
	private int ID;
	private String CPF;
	private String login;
	private String password;
	private String name;
	private List<String> address;
	private List<String> phones;
	private List<Game> wishList;
}
