package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Bar;
import org.varelacasas.models.entities.Camarero;

import java.util.List;

@RepositoryJpa
@Repository
public class CamareroRepositoryJpaImpl implements CrudRepositoryInterface<Camarero> {

    @Inject
    private EntityManager em;

    @Override
    public Camarero get(int id) throws Exception {
        return em.find(Camarero.class, id);
    }

    @Override
    public List<Camarero> getAll() throws Exception {
        return em.createQuery("SELECT c FROM Camarero c", Camarero.class).getResultList();
    }

    @Override
    public void save(Camarero camarero) throws Exception {
        if(camarero.getId() != null && camarero.getId() > 0){
            em.merge(camarero);
        } else {
            em.persist(camarero);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
