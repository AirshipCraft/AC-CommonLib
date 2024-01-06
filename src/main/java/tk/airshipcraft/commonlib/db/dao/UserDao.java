package tk.airshipcraft.commonlib.db.dao;

import tk.airshipcraft.commonlib.db.GenericDao;
import tk.airshipcraft.commonlib.db.model.User;

import java.util.UUID;

/**
 * Interface for data access operations on User entities.
 * Extends GenericDao for standard CRUD operations and includes user-specific methods.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public interface UserDao extends GenericDao<User, UUID> {

    /**
     * Retrieves a User entity by its username.
     *
     * @param username The username of the User to retrieve.
     * @return The User entity with the specified username, or null if not found.
     */
    User findByUsername(String username);

    // Any additional user-specific methods can be defined here
}
