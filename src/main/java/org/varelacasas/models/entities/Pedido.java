package org.varelacasas.models.entities;

import jakarta.persistence.*;
import org.varelacasas.models.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Grupo grupo;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Bar bar;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbl_pedidos_consumiciones", joinColumns = @JoinColumn(name="id_pedido")
            , inverseJoinColumns = @JoinColumn(name = "id_consumicion")
            , uniqueConstraints = @UniqueConstraint(columnNames={"id_consumicion"}))
    private List<Consumicion> consumiciones;
    private Estado estado;
    @Column(name = "estado_cobro")
    private EstadoCobro estadoCobro;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Camarero camareroResponsable;
    @Embedded
    private FechaHorasPedidos fechaHorasPedidos = new FechaHorasPedidos();
    private float importeTotal;
    private float importeSatisfecho;
    private float importeRestante;

    @PrePersist
    public void prePersist(){
        this.estado = Estado.EN_COLA;
        this.estadoCobro = EstadoCobro.NADA;
    }

    public Pedido() {
        consumiciones = new ArrayList<>();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public List<Consumicion> getConsumiciones() {
        return consumiciones;
    }

    public void setConsumiciones(List<Consumicion> listaConsumiciones) {
        this.consumiciones = listaConsumiciones;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoCobro getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(EstadoCobro estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public Camarero getCamareroResponsable() {
        return camareroResponsable;
    }

    public void setCamareroResponsable(Camarero camareroResponsable) {
        this.camareroResponsable = camareroResponsable;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public FechaHorasPedidos getFechaHorasPedidos() {
        return fechaHorasPedidos;
    }

    public void setFechaHorasPedidos(FechaHorasPedidos fechaHorasPedidos) {
        this.fechaHorasPedidos = fechaHorasPedidos;
    }

    public float getImporteSatisfecho() {
        return importeSatisfecho;
    }

    public void setImporteSatisfecho(float importeSatisfecho) {
        this.importeSatisfecho = importeSatisfecho;
    }

    public float getImporteRestante() {
        return importeRestante;
    }

    public void setImporteRestante(float importeRestante) {
        this.importeRestante = importeRestante;
    }
}
