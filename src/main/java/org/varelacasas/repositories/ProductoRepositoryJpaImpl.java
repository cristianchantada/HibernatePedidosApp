package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepositoryInterface<Producto> {

    @Inject
    private EntityManager em;

    @Override
    public Producto get(int id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> getAll() throws Exception {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public void save(Producto producto) throws Exception {
        if(producto.getId() != null && producto.getId() > 0){
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
