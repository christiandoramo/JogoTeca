package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroService {
	private static List<Usuario> users = new ArrayList<>();
	private static File file = new File("users.dat");

	static {
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				users = (List<Usuario>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Erro ao ler arquivo de usuários!");
			}
		}
	}

	private CadastroService() {
	}

	public static boolean cadastrar(Usuario user) {
		if (users.stream().anyMatch(u -> u.getCpf().equals(user.getCpf()) || u.getLogin().equals(user.getLogin()))) {
			System.out.println("Já existe um usuário cadastrado com o mesmo CPF ou login!");
			return false;
		}

		users.add(user);
		salvar();
		return true;
	}


	public static List<Usuario> listar() {
		return new ArrayList<>(users);
	}

	private static void salvar() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(users);
		} catch (IOException e) {
			System.out.println("Erro ao salvar arquivo de usuários!");
		}
	}
}
