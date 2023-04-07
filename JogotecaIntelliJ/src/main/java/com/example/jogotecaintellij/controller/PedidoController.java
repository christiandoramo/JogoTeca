package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.model.GameItem;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.User;

import java.time.LocalDate;
import java.util.List;

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


    public void adicionarPedido(LocalDate vencimento, User user, List<GameItem> itens, OrderStatus status, Metodo metodoPagamento) throws ElementAlreadyExistsException {
        Pedido pedidos = new Pedido(lastId + 1, vencimento, user, itens, status, metodoPagamento);
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

    public void updatePedidoById(int id, LocalDate vencimento, List<GameItem> itens, User user) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
        Pedido pedido = buscarPeloId(id);
        if (pedido != null) {
            if (itens != null) pedido.setItens(itens);
            if (user != null) pedido.setUser(user);
            if (vencimento != null) pedido.setVencimento(vencimento);
            pedidoRepositorio.update(pedido);
        }
    }

    public void destroyPedidoPeloId(int id) throws ElementDoesNotExistException {
        Pedido pedido = buscarPeloId(id);
        pedidoRepositorio.delete(pedido);
        lastId--;
    }
}
