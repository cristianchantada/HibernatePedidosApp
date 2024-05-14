package org.varelacasas.services;

import jakarta.inject.Inject;
import org.varelacasas.configs.Service;
import org.varelacasas.interceptors.TransactionalJpa;
import org.varelacasas.models.entities.Pedido;
import org.varelacasas.repositories.CrudRepositoryInterface;
import org.varelacasas.repositories.RepositoryJpa;
import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class PedidoServiceJpaImpl implements PedidoService {

    @Inject
    @RepositoryJpa
    private CrudRepositoryInterface<Pedido> repository;

    @Override
    public List<Pedido> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Pedido> get(Integer id) {
        try {
            return Optional.ofNullable(repository.get(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Pedido pedido) {
        try {
            repository.save(pedido);
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
