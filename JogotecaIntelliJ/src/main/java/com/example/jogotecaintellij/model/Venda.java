package com.example.jogotecaintellij.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private List<String> getDadosBancarios;
    private LocalDateTime momento;
    private Pedido pedido;
    private List<String> dadosBancarios;

    public Venda( Pedido pedido, List<String> dadosBancarios) {
        this.momento = momento;
        this.pedido = pedido;
        this.dadosBancarios = dadosBancarios;
        momento = LocalDateTime.now();
    }

    public Venda(Venda venda) {
        pedido = venda.getPedido();
        dadosBancarios = venda.getDadosBancarios;
        momento = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getMomento() {
        return momento;
    }
    public Pedido getPedido() {
        return pedido;
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

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }
}
