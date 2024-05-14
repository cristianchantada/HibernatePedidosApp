package org.varelacasas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class FechaHorasPedidos {

    @Column(name = "fecha_hora_pedido")
    private LocalDateTime fechaHoraPedido;
    @Column(name = "fecha_hora_en_proceso")
    private LocalDateTime fechaHoraEnProceso;
    @Column(name = "fecha_hora_despachado")
    private LocalDateTime fechaHoraDespachado;
    @Column(name = "fecha_hora_cerrado")
    private LocalDateTime fechaHoraCerrado;
    @Column(name = "fecha_hora_modificado")
    private LocalDateTime fechaHoraModificado;
    @Column(name = "fecha_hora_cancelado")
    private LocalDateTime fechaHoraCancelado;

    @PrePersist
    public void prePersist() {
        System.out.println("Se inicializa pedido con fecha-hora de ahora mismo");
        this.fechaHoraPedido = LocalDateTime.now();
    }

    public LocalDateTime getFechaHoraPedido() {
        return fechaHoraPedido;
    }

    public void setFechaHoraPedido(LocalDateTime fechaHoraPedido) {
        this.fechaHoraPedido = fechaHoraPedido;
    }

    public LocalDateTime getFechaHoraEnProceso() {
        return fechaHoraEnProceso;
    }

    public void setFechaHoraEnProceso(LocalDateTime fechaHoraEnProceso) {
        this.fechaHoraEnProceso = fechaHoraEnProceso;
    }

    public LocalDateTime getFechaHoraDespachado() {
        return fechaHoraDespachado;
    }

    public void setFechaHoraDespachado(LocalDateTime fechaHoraDespachado) {
        this.fechaHoraDespachado = fechaHoraDespachado;
    }

    public LocalDateTime getFechaHoraCerrado() {
        return fechaHoraCerrado;
    }

    public void setFechaHoraCerrado(LocalDateTime fechaHoraCerrado) {
        this.fechaHoraCerrado = fechaHoraCerrado;
    }

    public LocalDateTime getFechaHoraModificado() {
        return fechaHoraModificado;
    }

    public void setFechaHoraModificado(LocalDateTime fechaHoraModificado) {
        this.fechaHoraModificado = fechaHoraModificado;
    }

    public LocalDateTime getFechaHoraCancelado() {
        return fechaHoraCancelado;
    }

    public void setFechaHoraCancelado(LocalDateTime fechaHoraCancelado) {
        this.fechaHoraCancelado = fechaHoraCancelado;
    }
}
