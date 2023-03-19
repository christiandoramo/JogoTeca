package br.jogoteca.system.application;

import java.util.ArrayList;

public class UserController {
    
    private ArrayList<User> users;

    public UserController() {
        this.users = new ArrayList<User>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public User getUserById(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return this.users;
    }
}
