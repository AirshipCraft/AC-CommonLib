package tk.airshipcraft.commonlib.db.dao.impl;

import tk.airshipcraft.commonlib.db.SqlConnectionManager;
import tk.airshipcraft.commonlib.db.dao.AuthDataDao;
import tk.airshipcraft.commonlib.db.model.AuthData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * SQL implementation of the AuthDataDao interface.
 * This class is responsible for performing CRUD operations on AuthData entities in the database.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-05
 */
public class SqlAuthDataDao implements AuthDataDao {

    private final SqlConnectionManager connectionManager;

    /**
     * Constructs an instance of SqlAuthDataDao with a given SqlConnectionManager.
     *
     * @param connectionManager The connection manager responsible for managing database connections.
     */
    public SqlAuthDataDao(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Finds an AuthData record by its ID.
     *
     * @param id the entity's identifier.
     * @return An {@code Optional<AuthData>} containing the AuthData if found, otherwise empty.
     */
    @Override
    public Optional<AuthData> findById(UUID id) {
        // SQL query to find AuthData by ID
        String sql = "SELECT * FROM auth_data WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToAuthData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper error handling
        }
        return Optional.empty();
    }

    /**
     * Finds all AuthData records.
     *
     * @return A list of all AuthData records.
     */
    @Override
    public List<AuthData> findAll() {
        // SQL query to find all AuthData records
        List<AuthData> authDataList = new ArrayList<>();
        String sql = "SELECT * FROM auth_data";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                authDataList.add(mapResultSetToAuthData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper error handling
        }
        return authDataList;
    }

    /**
     * Creates a new AuthData record.
     *
     * @param authData The AuthData object to create.
     * @return The created AuthData object.
     */
    @Override
    public AuthData create(AuthData authData) {
        // SQL query to create new AuthData record
        String sql = "INSERT INTO auth_data (id, ign, username, token, verified) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForAuthData(stmt, authData);
            stmt.executeUpdate();
            return authData;
        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper error handling
        }
        return null;
    }

    /**
     * Updates an existing AuthData record.
     *
     * @param authData The AuthData object to update.
     * @return The updated AuthData object.
     */
    @Override
    public AuthData update(AuthData authData) {
        // SQL query to update AuthData record
        String sql = "UPDATE auth_data SET ign = ?, username = ?, token = ?, verified = ? WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForAuthData(stmt, authData);
            stmt.setObject(5, authData.getId());
            stmt.executeUpdate();
            return authData;
        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper error handling
        }
        return null;
    }

    /**
     * Deletes an existing AuthData record by its ID.
     *
     * @param id The ID of the AuthData record to delete.
     */
    @Override
    public void deleteById(UUID id) {
        // SQL query to delete AuthData record by ID
        String sql = "DELETE FROM auth_data WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Replace with proper error handling
        }
    }

    /**
     * Maps a ResultSet to an AuthData object.
     *
     * @param rs The ResultSet from which to map.
     * @return An AuthData object.
     * @throws SQLException If a database access error occurs.
     */
    private AuthData mapResultSetToAuthData(ResultSet rs) throws SQLException {
        return new AuthData(
                UUID.fromString(rs.getString("id")),
                rs.getString("ign"),
                rs.getString("username"),
                UUID.fromString(rs.getString("token")),
                rs.getBoolean("verified")
        );
    }

    /**
     * Prepares a PreparedStatement with AuthData fields.
     *
     * @param stmt     The PreparedStatement to prepare.
     * @param authData The AuthData object from which to take the data.
     * @throws SQLException If a database access error occurs.
     */
    private void prepareStatementForAuthData(PreparedStatement stmt, AuthData authData) throws SQLException {
        stmt.setObject(1, authData.getId());
        stmt.setString(2, authData.getIGN());
        stmt.setString(3, authData.getUsername());
        stmt.setObject(4, authData.getToken());
        stmt.setBoolean(5, authData.isVerified());
    }
}
