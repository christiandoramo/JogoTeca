package br.jogoteca.system.controllers;

import java.util.ArrayList;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.Customer;
import br.jogoteca.system.models.User;

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
    
    public void updateUser(User updatedUser) {
        boolean userUpdated = false;
        for (int i = 0; i < this.users.size() && !userUpdated; i++) {
            if (this.users.get(i).getId() == updatedUser.getId()) {
                this.users.set(i, updatedUser);
                userUpdated = true;
            }
        }
    }

    public ArrayList<User> getAllUsers() {
        return this.users;
    }
    
    
    //Para uso de arquivos na SPRINT 4/5
//	private GenericRepository<Customer> customerRepository;
//	
//	private static CustomersController instance;
//	
//	private CustomersController() {
//        this.customerRepository = new GenericRepository<>("customers.dat");
//    }
//	
//    public static CustomersController getInstance() {
//        if (instance == null) {
//            instance = new CustomersController();
//        }
//        return instance;
//    }
}
