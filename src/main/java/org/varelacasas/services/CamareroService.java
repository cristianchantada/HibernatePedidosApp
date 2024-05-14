package org.varelacasas.services;

import org.varelacasas.models.entities.Camarero;
import org.varelacasas.models.entities.Consumicion;

import java.util.List;
import java.util.Optional;

public interface CamareroService {

    List<Camarero> getAll();

    Optional<Camarero> get(Integer id);

    void save(Camarero t);

    void delete(Integer id);
}
