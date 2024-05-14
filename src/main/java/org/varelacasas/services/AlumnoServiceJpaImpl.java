package org.varelacasas.services;

import jakarta.inject.Inject;
import org.varelacasas.configs.Service;
import org.varelacasas.interceptors.TransactionalJpa;
import org.varelacasas.models.entities.Alumno;
import org.varelacasas.models.entities.Bar;
import org.varelacasas.repositories.CrudRepositoryInterface;
import org.varelacasas.repositories.RepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class AlumnoServiceJpaImpl implements AlumnoService {

    @Inject
    @RepositoryJpa
    private CrudRepositoryInterface<Alumno> repository;

    @Override
    public List<Alumno> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Alumno> get(Integer id) {
        try {
            return Optional.ofNullable(repository.get(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Alumno alumno) {
        try {
            repository.save(alumno);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.delete(id);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

}
