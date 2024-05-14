package org.varelacasas.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.configs.Repository;
import org.varelacasas.models.entities.Alumno;

import java.util.List;

@RepositoryJpa
@Repository
public class AlumnoRepositoryJpaImpl implements CrudRepositoryInterface<Alumno> {

    @Inject
    private EntityManager em;

    @Override
    public Alumno get(int id) throws Exception {
        return em.find(Alumno.class, id);
    }

    @Override
    public List<Alumno> getAll() throws Exception {
        return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
    }

    @Override
    public void save(Alumno alumno) throws Exception {
        if(alumno.getId() != null && alumno.getId() > 0){
            em.merge(alumno);
        } else {
            em.persist(alumno);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        em.remove(get(id));
    }

}
