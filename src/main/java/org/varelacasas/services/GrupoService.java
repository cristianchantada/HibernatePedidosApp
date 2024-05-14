package org.varelacasas.services;

import org.varelacasas.models.entities.Grupo;

import java.util.List;
import java.util.Optional;

public interface GrupoService {

    List<Grupo> getAll();

    Optional<Grupo> get(Integer id);

    void save(Grupo t);

    void delete(Integer id);
}
