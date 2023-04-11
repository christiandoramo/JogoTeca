package com.example.jogotecaintellij.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SessaoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDateTime logon;
    private LocalDateTime logoff;
    private Usuario usuario;

    public SessaoUsuario(Usuario usuario){
        this.usuario = usuario;
        logon = LocalDateTime.now();
    }
    public void setLogoff() {

        logoff = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessaoUsuario that = (SessaoUsuario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
