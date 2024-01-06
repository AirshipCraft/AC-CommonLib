package tk.airshipcraft.commonlib.db.dao.impl;

import tk.airshipcraft.commonlib.db.SqlConnectionManager;
import tk.airshipcraft.commonlib.db.dao.WarningDao;
import tk.airshipcraft.commonlib.db.model.Warning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides SQL-specific data access operations for Warning objects.
 * Implements the WarningDao interface using a SQL database.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
public class SqlWarningDao implements WarningDao {

    private final SqlConnectionManager connectionManager;

    /**
     * Constructs a SqlWarningDao with a given SqlConnectionManager.
     *
     * @param connectionManager The connection manager for database connections.
     */
    public SqlWarningDao(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Retrieves a Warning by its unique ID.
     *
     * @param id The UUID of the Warning.
     * @return An Optional containing the Warning if found, or an empty Optional otherwise.
     */
    @Override
    public Optional<Warning> findById(UUID id) {
        String sql = "SELECT * FROM warnings WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToWarning(resultSet));
            }
        } catch (SQLException e) {
            // Log and handle exception
        }
        return Optional.empty();
    }

    /**
     * Retrieves all Warnings from the database.
     *
     * @return A List of all Warnings.
     */
    @Override
    public List<Warning> findAll() {
        String sql = "SELECT * FROM warnings";
        List<Warning> warnings = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                warnings.add(mapRowToWarning(resultSet));
            }
        } catch (SQLException e) {
            // Log and handle exception
        }
        return warnings;
    }

    /**
     * Creates a new Warning in the database.
     *
     * @param entity The Warning to be created.
     * @return The created Warning.
     */
    @Override
    public Warning create(Warning entity) {
        String sql = "INSERT INTO warnings (id, issuer, minecraft_recipient, discord_recipient, reason, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIssuer());
            statement.setObject(3, entity.getMinecraftRecipient());
            statement.setString(4, entity.getDiscordRecipient());
            statement.setString(5, entity.getReason());
            statement.setLong(6, entity.getTimestamp());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Log and handle exception
        }
        return entity;
    }

    /**
     * Updates an existing Warning in the database.
     *
     * @param entity The Warning to be updated.
     * @return The updated Warning.
     */
    @Override
    public Warning update(Warning entity) {
        String sql = "UPDATE warnings SET issuer = ?, minecraft_recipient = ?, discord_recipient = ?, reason = ?, timestamp = ? WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, entity.getIssuer());
            statement.setObject(2, entity.getMinecraftRecipient());
            statement.setString(3, entity.getDiscordRecipient());
            statement.setString(4, entity.getReason());
            statement.setLong(5, entity.getTimestamp());
            statement.setObject(6, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Log and handle exception
        }
        return entity;
    }

    /**
     * Deletes a Warning from the database by its UUID.
     *
     * @param uuid The UUID of the Warning to be deleted.
     */
    @Override
    public void deleteById(UUID uuid) {
        String sql = "DELETE FROM warnings WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Log and handle exception
        }
    }

    /**
     * Retrieves all Warnings issued to a specific Minecraft or Discord user.
     *
     * @param minecraftId The UUID of the Minecraft user.
     * @param discordId The Discord ID of the user.
     * @return A List of Warnings associated with the given user.
     */
    @Override
    public List<Warning> findByUser(UUID minecraftId, String discordId) {
        String sql = "SELECT * FROM warnings WHERE minecraft_recipient = ? OR discord_recipient = ?";
        List<Warning> warnings = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, minecraftId);
            statement.setString(2, discordId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                warnings.add(mapRowToWarning(resultSet));
            }
        } catch (SQLException e) {
            // Log and handle exception
        }
        return warnings;
    }

    /**
     * Maps a row from the ResultSet to a Warning object.
     *
     * @param resultSet The ResultSet containing the data.
     * @return The Warning object mapped from the ResultSet.
     * @throws SQLException If an SQL error occurs.
     */
    private Warning mapRowToWarning(ResultSet resultSet) throws SQLException {
        return new Warning(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("issuer"),
                (UUID) resultSet.getObject("minecraft_recipient"),
                resultSet.getString("discord_recipient"),
                resultSet.getString("reason"),
                resultSet.getLong("timestamp")
        );
    }
}
