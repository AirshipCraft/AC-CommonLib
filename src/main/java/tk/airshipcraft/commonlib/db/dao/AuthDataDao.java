package tk.airshipcraft.commonlib.db.dao;

import tk.airshipcraft.commonlib.db.GenericDao;
import tk.airshipcraft.commonlib.db.model.AuthData;

import java.util.UUID;

/**
 * Interface for data access operations related to AuthData.
 * This interface extends GenericDao and defines additional methods specific to AuthData.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-05
 */
public interface AuthDataDao extends GenericDao<AuthData, UUID> {

    /**
     * Inserts new AuthData into the database.
     * This method should handle the creation of new AuthData records.
     *
     * @param authData The AuthData to insert.
     * @return The inserted AuthData.
     */
    AuthData create(AuthData authData);

    /**
     * Updates existing AuthData in the database.
     * This method should handle modifications to existing AuthData records.
     *
     * @param authData The AuthData to update.
     * @return The updated AuthData.
     */
    AuthData update(AuthData authData);

    /**
     * Deletes AuthData for a given player's UUID from the database.
     * This method should remove the AuthData associated with the specified UUID.
     *
     * @param id The UUID of the player.
     */
    void deleteById(UUID id);
}
