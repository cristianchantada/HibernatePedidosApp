package org.varelacasas.models;

import java.util.Objects;

public enum EstadoCobroConsumicion {

    PENDIENTE("Pendiente"),
    PAGADO("Pagado");

    private String estado;

    EstadoCobroConsumicion(String estado){
        this.estado = estado;
    }

    public String getEstado(){
        return this.estado;
    }

    public boolean isPaid(EstadoCobroConsumicion estadoCobroConsumicion){
        return estadoCobroConsumicion == PAGADO;
    }

    public static Estado porCodigo(String estadoPedido) {
        for (Estado estado : Estado.values()) {
            if (Objects.equals(estado.getEstado(), estadoPedido)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Código de estado inválido: " + estadoPedido);
    }

}
