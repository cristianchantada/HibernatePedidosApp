package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Grupo;

import java.util.List;

@RepositoryJpa
@Repository
public class GrupoRepositoryJpaImpl implements CrudRepositoryInterface<Grupo> {

    @Inject
    private EntityManager em;

    @Override
    public Grupo get(int id) throws Exception {
        return em.find(Grupo.class, id);
    }

    @Override
    public List<Grupo> getAll() throws Exception {
        return em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
    }

    @Override
    public void save(Grupo Grupo) throws Exception {
        if(Grupo.getId() != null && Grupo.getId() > 0){
            em.merge(Grupo);
        } else {
            em.persist(Grupo);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
