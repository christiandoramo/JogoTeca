package com.example.jogotecaintellij.model;


import com.example.jogotecaintellij.enums.StatusJogo;

import java.io.Serializable;

public class ItemJogo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double value;
    //////////////////////////////////////////////////////////////////////////////////
    private Jogo game;
    private StatusJogo status;

    public Jogo getGame() {
        return game;
    }

    public void setGame(Jogo game) {
        this.game = game;
    }

    public ItemJogo(double value, Jogo game) {
        super();
        this.value = value;
        this.game = game;
        status = StatusJogo.DISPONIVEL;
    }

    public ItemJogo(double value, Jogo game, StatusJogo status) {
        this.value = value;
        this.game = game;
        this.status = status;
    }

    public ItemJogo(Jogo game) {
        value = game.getPrice();
        this.game = game;
        status = StatusJogo.DISPONIVEL;
    }

    public StatusJogo getStatus() {
        return status;
    }

    public void setStatus(StatusJogo status) {
        this.status = status;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        ItemJogo other = (ItemJogo) obj;
        if (id != other.id)
            return false;
        return true;
    }

}