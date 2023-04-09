package br.jogoteca.system.controllers;

import java.time.LocalDateTime;
import java.util.List;

import br.jogoteca.system.data.GenericRepository;
import br.jogoteca.system.data.IGenericRepository;
import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;
import br.jogoteca.system.exceptions.ElementWithSameNameExistsException;
import br.jogoteca.system.exceptions.ElementsDoNotExistException;
import br.jogoteca.system.models.Pedido;
import br.jogoteca.system.models.Venda;

public class VendaController {
	 private IGenericRepository<Venda> vendasRepository;
	    private int lastId;

	    private static VendaController instance;

	    private VendaController() {
	        this.vendasRepository = new GenericRepository<>("vendas.dat");
	        lastId = vendasRepository.read().size();
	    }

	    public static VendaController getInstance() {
	        if (instance == null) {
	            instance = new VendaController();
	        }
	        return instance;
	    }

	    //(int id, String name, LocalDate releaseDate, Genre genre, String description, String publicadora, String desenvolvedora, Double price, String imageURL, String videoUrl, List<String> imagesUrl, StatusJogo status
	    public void insertVenda(Pedido pedido, List<String> dadosBancarios) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
	        Venda novo = new Venda(lastId + 1, LocalDateTime.now(), pedido, dadosBancarios);
	        vendasRepository.insert(novo);
	        lastId++;
	    }


	    public Venda searchVendaById(int id) throws ElementDoesNotExistException {
	        return vendasRepository.read().stream().filter(venda -> venda.getId() == id).findFirst().orElse(null);
	    }

	    public List<Venda> searchAllVendas() throws ElementsDoNotExistException {
	        return vendasRepository.read();
	    }

	    public void updateVenda(Venda venda, LocalDateTime dataNova, List<String> dadosBancarios) throws ElementDoesNotExistException {
	        if (venda != null) {
	            if (dataNova != null && !dataNova.equals(""))
	                venda.setMomento(dataNova);
	            if (dadosBancarios != null)
	                venda.setDadosBancarios(dadosBancarios);
	            vendasRepository.update(venda);
	        }
	    }

	    public void updateVendaById(int id, LocalDateTime dataNova, List<String> dadosBancarios) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
	        Venda venda = searchVendaById(id);
	        updateVenda(venda, dataNova, dadosBancarios);
	    }

	    public void destroyVendaById(int id) throws ElementDoesNotExistException {
	        Venda venda = searchVendaById(id);
	        vendasRepository.delete(venda);
	        lastId--;

	    }
	
	
}
