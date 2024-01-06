package tk.airshipcraft.commonlib.db.dao.impl;

import tk.airshipcraft.commonlib.db.SqlConnectionManager;
import tk.airshipcraft.commonlib.db.dao.AuthDataDao;
import tk.airshipcraft.commonlib.db.model.AuthData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the AuthDataDao interface using SQL.
 * This class handles CRUD operations related to AuthData objects in the SQL database.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-05
 */
public class SqlAuthDataDao implements AuthDataDao {

    private final SqlConnectionManager connectionManager;

    /**
     * Constructs an SqlAuthDataDao with a given SqlConnectionManager.
     *
     * @param connectionManager The manager responsible for providing database connections.
     */
    public SqlAuthDataDao(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Finds an AuthData object by its UUID.
     *
     * @param id The UUID of the AuthData object.
     * @return An Optional containing the AuthData if found, or an empty Optional if not.
     */
    @Override
    public Optional<AuthData> findById(UUID id) {
        String sql = "SELECT * FROM auth_data WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToAuthData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
        return Optional.empty();
    }

    /**
     * Inserts a new AuthData object into the database.
     *
     * @param authData The AuthData object to insert.
     */
    @Override
    public void insert(AuthData authData) {
        String sql = "INSERT INTO auth_data (id, ign, username, token, verified) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForAuthData(stmt, authData);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
    }

    /**
     * Updates an existing AuthData object in the database.
     *
     * @param authData The AuthData object to update.
     */
    @Override
    public void update(AuthData authData) {
        String sql = "UPDATE auth_data SET ign = ?, username = ?, token = ?, verified = ? WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForAuthData(stmt, authData);
            stmt.setString(5, authData.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
    }

    /**
     * Deletes an AuthData object from the database by its UUID.
     *
     * @param id The UUID of the AuthData object to delete.
     */
    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM auth_data WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
    }

    /**
     * Maps a ResultSet to an AuthData object.
     *
     * @param rs The ResultSet to map.
     * @return The AuthData object created from the ResultSet.
     * @throws SQLException If a database access error occurs.
     */
    private AuthData mapResultSetToAuthData(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String ign = rs.getString("ign");
        String username = rs.getString("username");
        UUID token = UUID.fromString(rs.getString("token"));
        boolean verified = rs.getBoolean("verified");

        return new AuthData(id, ign, username, token, verified);
    }

    /**
     * Prepares a PreparedStatement with an AuthData object's data.
     *
     * @param stmt     The PreparedStatement to prepare.
     * @param authData The AuthData object to use for setting statement values.
     * @throws SQLException If a database access error occurs.
     */
    private void prepareStatementForAuthData(PreparedStatement stmt, AuthData authData) throws SQLException {
        stmt.setString(1, authData.getId().toString());
        stmt.setString(2, authData.getIGN());
        stmt.setString(3, authData.getUsername());
        stmt.setString(4, authData.getToken().toString());
        stmt.setBoolean(5, authData.isVerified());
    }
}
