package tk.airshipcraft.commonlib.db;

import java.util.List;
import java.util.Optional;

/**
 * The GenericDao interface defines the standard CRUD operations to be performed on a model object.
 * This interface is generic and can be implemented for any type of model object.
 *
 * @param <T>  the type of the model object
 * @param <ID> the type of the model object's identifier
 */
public interface GenericDao<T, ID> {

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the entity's identifier.
     * @return an Optional containing the found entity or an empty Optional if no entity is found.
     */
    Optional<T> findById(ID id);

    /**
     * Retrieves all entities of type T.
     *
     * @return a list of all entities of type T.
     */
    List<T> findAll();

    /**
     * Creates and saves a new entity.
     *
     * @param entity the entity to be saved.
     * @return the saved entity, now persisted in the data store.
     */
    T create(T entity);

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update.
     * @return the updated entity.
     */
    T update(T entity);

    /**
     * Deletes the entity with the given identifier.
     *
     * @param id the identifier of the entity to delete.
     */
    void deleteById(ID id);
}
