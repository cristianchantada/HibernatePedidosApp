package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Pedido;

import java.util.List;

@RepositoryJpa
@Repository
public class PedidoRepositoryJpaImpl implements CrudRepositoryInterface<Pedido> {

    @Inject
    private EntityManager em;

    @Override
    public Pedido get(int id) throws Exception {
        return em.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> getAll() throws Exception {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    @Override
    public void save(Pedido pedido) throws Exception {
        if(pedido.getId() != null && pedido.getId() > 0){
            em.merge(pedido);
        } else {
            em.persist(pedido);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
