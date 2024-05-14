package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Bar;
import org.varelacasas.models.entities.Pedido;

import java.util.List;

@RepositoryJpa
@Repository
public class BarRepositoryJpaImpl implements CrudRepositoryInterface<Bar> {

    @Inject
    private EntityManager em;

    @Override
    public Bar get(int id) throws Exception {
        return em.find(Bar.class, id);
    }

    @Override
    public List<Bar> getAll() throws Exception {
        return em.createQuery("SELECT b FROM Bar b", Bar.class).getResultList();
    }

    @Override
    public void save(Bar bar) throws Exception {
        if(bar.getId() != null && bar.getId() > 0){
            em.merge(bar);
        } else {
            em.persist(bar);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
