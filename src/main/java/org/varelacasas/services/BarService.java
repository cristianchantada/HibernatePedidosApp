package org.varelacasas.services;

import org.varelacasas.models.entities.Bar;

import java.util.List;
import java.util.Optional;

public interface BarService {

    List<Bar> getAll();

    Optional<Bar> get(Integer id);

    void save(Bar t);

    void delete(Integer id);
}
