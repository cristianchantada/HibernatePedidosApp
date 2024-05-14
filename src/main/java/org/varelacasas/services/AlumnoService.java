package org.varelacasas.services;

import org.varelacasas.models.entities.Alumno;
import org.varelacasas.models.entities.Bar;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<Alumno> getAll();

    Optional<Alumno> get(Integer id);

    void save(Alumno t);

    void delete(Integer id);
}
