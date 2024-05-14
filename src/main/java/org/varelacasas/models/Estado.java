package org.varelacasas.models;

import java.util.Objects;

public enum Estado {
    EN_COLA("En cola"),
    EN_PROCESO("En proceso"),
    DESPACHADO("Despachado"),
    CERRADO("Cerrado"),
    EN_MODIFICACION("En modificación"),
    CANCELADO("Cancelado");

    private String estado;

    Estado(String estado){
        this.estado = estado;
    }

    public String getEstado(){
        return this.estado;
    }

    public boolean isClosed(Estado estado){
        return estado == CERRADO;
    }

}
