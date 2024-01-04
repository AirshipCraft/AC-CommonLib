package tk.airshipcraft.commonlib.db;

import java.util.List;
import java.util.Optional;

/**
 * The GenericDao interface defines the standard operations to be performed on a model object(s).
 *
 * @param <T>  the type of the model object
 * @param <ID> the type of the model object's identifier
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public interface GenericDao<T, ID> {

    /**
     * Retrieves an entity by its id.
     *
     * @param id the entity's identifier
     * @return an Optional containing the found entity or an empty Optional if no entity is found
     */
    Optional<T> findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return a list of entities
     */
    List<T> findAll();

    /**
     * Saves a given entity.
     *
     * @param entity entity to save
     * @return the saved entity
     */
    T create(T entity);

    /**
     * Updates a given entity.
     *
     * @param entity entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes the entity with the given id.
     *
     * @param id the id of the entity to delete
     */
    void deleteById(ID id);
}
