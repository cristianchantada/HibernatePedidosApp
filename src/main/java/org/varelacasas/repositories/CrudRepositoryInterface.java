package org.varelacasas.repositories;

import java.util.List;

public interface CrudRepositoryInterface<T> {

    T get(int id) throws Exception;

    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void delete(int id) throws Exception;
}
