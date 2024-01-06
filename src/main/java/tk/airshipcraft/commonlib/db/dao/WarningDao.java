package tk.airshipcraft.commonlib.db.dao;

import tk.airshipcraft.commonlib.db.GenericDao;
import tk.airshipcraft.commonlib.db.model.Warning;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The WarningDao interface defines the data access methods for Warning objects.
 * Extends GenericDao to provide standard CRUD operations for Warning entities.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
public interface WarningDao extends GenericDao<Warning, UUID> {

    @Override
    Optional<Warning> findById(UUID id);

    @Override
    List<Warning> findAll();

    @Override
    Warning create(Warning entity);

    @Override
    Warning update(Warning entity);

    @Override
    void deleteById(UUID id);

    /**
     * Retrieves all warnings issued to a specific Minecraft or Discord user.
     *
     * @param minecraftId The UUID of the Minecraft user.
     * @param discordId The Discord ID of the user.
     * @return A List of Warning objects.
     */
    List<Warning> findByUser(UUID minecraftId, String discordId);

    /**
     * Saves a new warning to the database.
     *
     * @param warning The Warning object to save.
     */
    void save(Warning warning);

    /**
     * Deletes a warning from the database.
     *
     * @param id The UUID of the warning to delete.
     */
    void delete(UUID id);
}
