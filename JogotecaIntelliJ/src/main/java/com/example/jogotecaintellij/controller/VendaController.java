package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Usuario;
import com.example.jogotecaintellij.model.Venda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VendaController {
    private IGenericRepository<Venda> vendasRepository;

    private static VendaController instance;

    private VendaController() {
        this.vendasRepository = new GenericRepository<>("vendas.dat");
    }

    public static VendaController getInstance() {
        if (instance == null) {
            instance = new VendaController();
        }
        return instance;
    }

    //(int id, String name, LocalDate releaseDate, Genre genre, String description, String publicadora, String desenvolvedora, Double price, String imageURL, String videoUrl, List<String> imagesUrl, StatusJogo status
    public void insertVenda(Pedido pedido, List<String> dadosBancarios) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
        Venda novo = new Venda( pedido, dadosBancarios);
        vendasRepository.insert(novo);
    }
    public void insertVenda(Venda venda) throws ElementAlreadyExistsException, ElementWithSameNameExistsException {
        vendasRepository.insert(venda);
    }


    public Venda searchVendaByPedidoId(int id) throws ElementDoesNotExistException {
        return vendasRepository.read().stream().filter(venda -> venda.getPedido().getId() == id).findFirst().orElse(null);
    }

    public Venda searchVendaById(int id) throws ElementDoesNotExistException {
        return vendasRepository.read().stream().filter(venda -> venda.getId() == id).findFirst().orElse(null);
    }

    public Venda searchVendaByPedido(Pedido pedido) throws ElementDoesNotExistException {
        return vendasRepository.read().stream().filter(pedid -> pedid.getPedido().equals(pedido)).findFirst().orElse(null);
    }
    public Venda searchVendaByNumero(String numero) throws ElementDoesNotExistException {
        return vendasRepository.read().stream().filter(pedid -> pedid.getPedido().equals(numero)).findFirst().orElse(null);
    }
    public List<Venda> searchAllVendas() throws ElementsDoNotExistException {
        return vendasRepository.read();
    }
    public List<Venda> buscarListaDeComprasDoUsuario(Usuario usuario) throws ElementsDoNotExistException {
        return searchAllVendas().stream().filter(x -> x.getPedido().getUser().equals(usuario)).collect(Collectors.toList());
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

    public List<Venda> searchAllUsers() throws ElementsDoNotExistException {
        return vendasRepository.read();
    }

    public void updateVendaById(int id, LocalDateTime dataNova, List<String> dadosBancarios) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
        updateVenda(searchVendaById(id), dataNova, dadosBancarios);
    }

    public void destroyVendaById(int id) throws ElementDoesNotExistException {
        vendasRepository.delete(searchVendaById(id));
    }
}
