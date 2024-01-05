package tk.airshipcraft.commonlib.db.dao;

import tk.airshipcraft.commonlib.db.model.AuthData;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for data access operations related to AuthData.
 * Defines methods for querying, inserting, updating, and deleting AuthData.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-05
 */
public interface AuthDataDao {

    /**
     * Retrieves AuthData for a given player's UUID.
     *
     * @param id The UUID of the player.
     * @return An Optional containing the AuthData if found, or an empty Optional if not.
     */
    Optional<AuthData> findById(UUID id);

    /**
     * Inserts new AuthData into the database.
     *
     * @param authData The AuthData to insert.
     */
    void insert(AuthData authData);

    /**
     * Updates existing AuthData in the database.
     *
     * @param authData The AuthData to update.
     */
    void update(AuthData authData);

    /**
     * Deletes AuthData for a given player's UUID from the database.
     *
     * @param id The UUID of the player.
     */
    void delete(UUID id);
}
