package com.example.jogotecaintellij.model;

import com.example.jogotecaintellij.enums.Metodo;
import com.example.jogotecaintellij.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate vencimento;
    private Usuario user;
    private List<ItemJogo> itens;
    private OrderStatus status;
    private Metodo metodo;

    public Pedido(LocalDate vencimento, Usuario user, List<ItemJogo> itens, OrderStatus status, Metodo metodo) {
        this.vencimento = vencimento;
        this.user = user;
        this.itens = itens;
        this.status = status;
        this.metodo = metodo;
    }

    public int getId() {
        return id;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Usuario getUser() {
        return user;
    }

    public List<ItemJogo> getItens() {
        return itens;
    }

    public void setItens(List<ItemJogo> itens) {
        this.itens = itens;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Metodo getMetodo() {
        return metodo;
    }


    public void setMetodo(Metodo metodo) {
        this.metodo = metodo;
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

    public double totalValue() {
        if (itens != null)
            return itens.stream().mapToDouble(ItemJogo::getValue).sum();
        return 0.0;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
