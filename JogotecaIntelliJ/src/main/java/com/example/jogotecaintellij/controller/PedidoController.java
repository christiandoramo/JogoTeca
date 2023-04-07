package com.example.jogotecaintellij.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.model.GameItem;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.User;

public class PedidoController {

    private IGenericRepository<Pedido> pedidoRepositorio;
    private int lastId;

    private static PedidoController instance;

    private PedidoController() {
        this.pedidoRepositorio = new GenericRepository<>("Pedidos.dat");
        lastId = pedidoRepositorio.read().size();
        System.out.println(lastId);
    }

    public static PedidoController getInstance() {
        if (instance == null) {
            instance = new PedidoController();
        }
        return instance;
    }

    public void adicionarPedido(LocalDateTime momento, List<GameItem> itens, User user) throws ElementAlreadyExistsException {
        Pedido pedidos = new Pedido(lastId + 1, momento, itens, user);
        pedidoRepositorio.insert(pedidos);
        lastId++;
    }

    public Pedido buscarPeloId(int id) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getId() == id).findFirst().orElse(null);
    }

    public Pedido buscarPelouser(User user) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getUser().equals(user)).findFirst().orElse(null);
    }

    public Pedido buscarPeloGameItem(List<GameItem> itens) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getItens().equals(itens)).findFirst().orElse(null);
    }

    public void removerPedidoId(int id) throws ElementDoesNotExistException {
        Pedido pedido = buscarPeloId(id);
        pedidoRepositorio.delete(pedido);
        lastId--;
    }

    public void updatePedidoById(int id, LocalDateTime momento, List<GameItem> itens, User user) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
        Pedido pedido = buscarPeloId(id);
        if (pedido != null) {
            if (itens != null) pedido.setItens(itens);
            if (user != null) pedido.setUser(user);
            if (momento != null) pedido.setMomento(momento);
            pedidoRepositorio.update(pedido);
        }
    }

    public void destroyPedidoPeloId(int id) throws ElementDoesNotExistException {
        Pedido pedido = buscarPeloId(id);
        pedidoRepositorio.delete(pedido);
        lastId--;
    }
}
