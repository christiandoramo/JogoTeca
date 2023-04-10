package br.jogoteca.system.application.acessarea;

import br.jogoteca.system.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Cadastro {
    private static List<User> users = new ArrayList<>();
    private static File file = new File("users.dat");

    static {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao ler arquivo de usuários!");
            }
        }
    }

    private Cadastro() {
    }

    public static boolean cadastrar(User user) {
        if (users.stream().anyMatch(u -> u.getCPF().equals(user.getCPF()) || u.getLogin().equals(user.getLogin()))) {
            System.out.println("Já existe um usuário cadastrado com o mesmo CPF ou login!");
            return false;
        }

        users.add(user);
        salvar();
        return true;
    }
    

    public static List<User> listar() {
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
