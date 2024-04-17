package ru.brusnika.model.services;

import ru.brusnika.model.repositories.ExtendedJpaRepository;

import java.util.List;

public abstract class EntityService<T, ID> {

    protected abstract ExtendedJpaRepository<T, ID> getRepository();

    protected abstract String entityName();

    public T add(T entity) {
        return getRepository().save(entity);
    }

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public T getById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException(entityName() + " not found with id: " + id));
    }

    public T getByName(String name) {
        return getRepository().findByName(name)
                .orElseThrow(() -> new RuntimeException(entityName() + " not found with name: " + name));
    }

    public void update(ID id, T entityDetails) {
        T entity = getById(id);
        getRepository().save(entity);
    }

    public void delete(ID id) {
        T entity = getById(id);
        getRepository().delete(entity);
    }
}
