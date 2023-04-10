package com.example.jogotecaintellij.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDateTime momento;
    private Pedido pedido;
    private List<String> dadosBancarios;

    public Venda(int id, LocalDateTime momento, Pedido pedido, List<String> dadosBancarios) {
        this.id = id;
        this.momento = momento;
        this.pedido = pedido;
        this.dadosBancarios = dadosBancarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<String> getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(List<String> dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return id == venda.id && Objects.equals(momento, venda.momento) && Objects.equals(pedido, venda.pedido) && Objects.equals(dadosBancarios, venda.dadosBancarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, momento, pedido, dadosBancarios);
    }
}
