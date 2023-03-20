package br.jogoteca.system.controllers;

import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.models.Customer;

public class CustomersController {
	
	private GenericRepository<Customer> customerRepository;
	
	private static CustomersController instance;
	
	private CustomersController() {
        this.customerRepository = new GenericRepository<>("customers.dat");
    }
	
    public static CustomersController getInstance() {
        if (instance == null) {
            instance = new CustomersController();
        }
        return instance;
    }
    
	public void insertCustomer() {
		
	}
	public Customer searchCustomerByID(int id) {
		return null;
	}
	public Customer searchCustomerByName(String name) {
		return null;
	}
	public List<Customer> searchAllCustomers(){
		return null;
	}
	public void updateCustomer(int id) {
		
	}
	public void removeCustomer(int id) {
		
	}
}
