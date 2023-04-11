package com.example.jogotecaintellij.controller;

import com.example.jogotecaintellij.data.GenericRepository;
import com.example.jogotecaintellij.data.IGenericRepository;
import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;
import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;
import com.example.jogotecaintellij.exception.ElementWithSameNameExistsException;
import com.example.jogotecaintellij.exception.ElementsDoNotExistException;
import com.example.jogotecaintellij.model.Game;
import com.example.jogotecaintellij.model.ItemJogo;
import com.example.jogotecaintellij.model.Pedido;
import com.example.jogotecaintellij.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {

    private IGenericRepository<Pedido> pedidoRepositorio;
    private static PedidoController instance;

    private PedidoController() {
        this.pedidoRepositorio = new GenericRepository<>("pedidos.dat");
    }

    public static PedidoController getInstance() {
        if (instance == null) {
            instance = new PedidoController();
        }
        return instance;
    }

    public LocalDate novoPrazo(int nSemanas) {
        return LocalDate.now().plusWeeks(nSemanas);
    }

    public void adicionarPedido(LocalDate vencimento, Usuario user, List<ItemJogo> itens, OrderStatus status, Metodo metodoPagamento) throws ElementAlreadyExistsException {
        Pedido pedido = new Pedido(vencimento, user, itens, status, metodoPagamento);
        pedidoRepositorio.insert(pedido);
    }

    public void adicionarPedido(Pedido pedido) throws ElementAlreadyExistsException {
        pedidoRepositorio.insert(pedido);
    }

    public List<Pedido> buscarTodos() throws ElementsDoNotExistException {
        return pedidoRepositorio.read();
    }

    public Pedido buscarPeloId(int id) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getId() == id).findFirst().orElse(null);
    }

    public Pedido buscarPelouser(Usuario user) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getUser().equals(user)).findFirst().orElse(null);
    }

    public Pedido buscarPeloGameItem(List<ItemJogo> itens) throws ElementDoesNotExistException {
        return pedidoRepositorio.read().stream().filter(pedido -> pedido.getItens().equals(itens)).findFirst().orElse(null);
    }

    public void removerPedidoId(int id) throws ElementDoesNotExistException {
        Pedido pedido = buscarPeloId(id);
        pedidoRepositorio.delete(pedido);
    }

    public void updatePedidoById(int id, LocalDate vencimento, List<ItemJogo> itens, Usuario user, Metodo metodo) throws ElementDoesNotExistException, ElementWithSameNameExistsException {
        Pedido pedido = buscarPeloId(id);
        if (pedido != null) {
            if (itens != null) pedido.setItens(itens);
            if (user != null) pedido.setUser(user);
            if (vencimento != null) pedido.setVencimento(vencimento);
            if (metodo != null) pedido.setMetodo(metodo);
            pedidoRepositorio.update(pedido);
        }
    }

    public void destroyPedidoPeloId(int id) throws ElementDoesNotExistException {
        Pedido pedido = buscarPeloId(id);
        pedidoRepositorio.delete(pedido);
    }

    public boolean checaSeUmJogoJaFoiComprado(Usuario user, ItemJogo novoItem) {
        List<ItemJogo> itemsDoUsuario = null;
        List<Game> jogosDoUsuario = null;
        if (pedidoRepositorio.read() != null && !pedidoRepositorio.read().isEmpty()) {
            itemsDoUsuario = pedidoRepositorio.read()
                    .stream()
                    .filter(pedido -> pedido.getUser().equals(user))
                    .flatMap(pedido -> pedido.getItens().stream())
                    .collect(Collectors.toList());
        }
        if (itemsDoUsuario != null && !itemsDoUsuario.isEmpty()) {
            jogosDoUsuario = itemsDoUsuario.stream()
                    .map(item -> item.getGame()).collect(Collectors.toList());
            return jogosDoUsuario.contains(novoItem.getGame());
        }
        return false;
    }
}
