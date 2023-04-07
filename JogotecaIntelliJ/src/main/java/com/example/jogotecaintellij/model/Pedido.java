package com.example.jogotecaintellij.model;

import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate vencimento;
    private User user;
    private List<GameItem> itens;
    private OrderStatus status;
    private Metodo metodoPagamento;

    public Pedido(int id, LocalDate vencimento, User user, List<GameItem> itens, OrderStatus status, Metodo metodoPagamento) {
        this.id = id;
        this.vencimento = vencimento;
        this.user = user;
        this.itens = itens;
        this.status = status;
        this.metodoPagamento = metodoPagamento;
    }


    public Double totalValue() {
        return itens.stream().mapToDouble(x -> x.getValue()).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<GameItem> getItens() {
        return itens;
    }

    public void setItens(List<GameItem> itens) {
        this.itens = itens;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Metodo getMetodoPagamento() {
        return metodoPagamento;
    }


    public void setMetodoPagamento(Metodo metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
