package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Venda;

import java.time.LocalDateTime;
import java.util.List;

public class VendasController {
    private IGenericRepository<Venda> vendasRepository;
    private int lastId;

    private static VendasController instance;

    private VendasController() {
        this.vendasRepository = new GenericRepository<>("vendas.dat");
        lastId = vendasRepository.read().size();
    }

    public static VendasController getInstance() {
        if (instance == null) {
            instance = new VendasController();
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
