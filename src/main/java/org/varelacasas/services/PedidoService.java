package org.varelacasas.services;

import org.varelacasas.models.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> getAll();

    Optional<Pedido> get(Integer id);

    void save(Pedido pedido);

    void delete(Integer id);

}
