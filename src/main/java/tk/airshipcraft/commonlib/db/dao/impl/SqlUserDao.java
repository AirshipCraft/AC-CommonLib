package tk.airshipcraft.commonlib.db.dao.impl;

import tk.airshipcraft.commonlib.db.SqlConnectionManager;
import tk.airshipcraft.commonlib.db.dao.UserDao;
import tk.airshipcraft.commonlib.db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>SQL implementation of the UserDao interface for accessing and manipulating user data in a database.</p>
 *
 * <p>This class is responsible for performing CRUD operations on User entities in the database.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class SqlUserDao implements UserDao {

    private final SqlConnectionManager connectionManager;

    /**
     * Constructs a new SqlUserDao with a specified SqlConnectionManager.
     *
     * @param connectionManager The manager responsible for providing database connections.
     */
    public SqlUserDao(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Retrieves a user by their unique identifier (UUID).
     *
     * @param id The unique identifier of the user.
     * @return An {@code Optional<User>} containing the user if found, otherwise empty.
     */
    @Override
    public Optional<User> findById(UUID id) {
        final String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            // Log and handle the exception
        }
        return Optional.empty();
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A {@code List<User>} containing all users.
     */
    @Override
    public List<User> findAll() {
        final String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            // Log and handle the exception
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Inserts a new user into the database.
     *
     * @param user The {@code User} object to insert.
     * @return The {@code User} object after insertion, with any auto-generated keys filled in.
     */
    @Override
    public User create(User user) {
        final String sql = "INSERT INTO users (id, username, join_date, last_join, last_seen, total_playtime, total_deaths, total_kills, total_blocks_broken, fish_caught) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setTimestamp(3, user.getJoinDate());
            stmt.setTimestamp(4, user.getLastJoin());
            stmt.setTimestamp(5, user.getLastSeen());
            stmt.setLong(6, user.getTotalPlaytime());
            stmt.setInt(7, user.getTotalDeaths());
            stmt.setInt(8, user.getTotalKills());
            stmt.setInt(9, user.getTotalBlocksBroken());
            stmt.setInt(10, user.getFishCaught());
            stmt.executeUpdate();

            // Optionally retrieve and set any auto-generated keys
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
        return user;
    }


    /**
     * Updates an existing user in the database.
     *
     * @param user The {@code User} object to update.
     * @return The {@code User} object after it has been updated.
     */
    @Override
    public User update(User user) {
        final String sql = "UPDATE users SET username = ?, join_date = ?, last_join = ?, last_seen = ?, total_playtime = ?, total_deaths = ?, total_kills = ?, total_blocks_broken = ?, fish_caught = ? WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setTimestamp(2, user.getJoinDate());
            stmt.setTimestamp(3, user.getLastJoin());
            stmt.setTimestamp(4, user.getLastSeen());
            stmt.setLong(5, user.getTotalPlaytime());
            stmt.setInt(6, user.getTotalDeaths());
            stmt.setInt(7, user.getTotalKills());
            stmt.setInt(8, user.getTotalBlocksBroken());
            stmt.setInt(9, user.getFishCaught());
            stmt.setObject(10, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper error handling
        }
        return user;
    }

    /**
     * Deletes a user from the database by their UUID.
     *
     * @param id The UUID of the user to delete.
     */
    @Override
    public void deleteById(UUID id) {
        final String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Log and handle the exception
            e.printStackTrace();
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for.
     * @return The {@code User} object if found, otherwise null.
     */
    @Override
    public User findByUsername(String username) {
        final String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            // Log and handle the exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Maps a row in a ResultSet to a User object.
     *
     * @param rs The ResultSet containing user data.
     * @return A User object populated with data from the ResultSet row.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                (UUID) rs.getObject("id"),
                rs.getString("username"),
                rs.getTimestamp("join_date"),
                rs.getTimestamp("last_join"),
                rs.getTimestamp("last_seen"),
                rs.getLong("total_playtime"),
                rs.getInt("total_deaths"),
                rs.getInt("total_kills"),
                rs.getInt("total_blocks_broken"),
                rs.getInt("fish_caught")
        );
    }
}
