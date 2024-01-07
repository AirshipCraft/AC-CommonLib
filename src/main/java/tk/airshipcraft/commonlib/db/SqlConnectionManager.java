package tk.airshipcraft.commonlib.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * Manages SQL database connections using HikariCP for connection pooling.
 * This manager is responsible for creating and configuring a pool of database
 * connections that can be reused for database operations, improving performance
 * by reducing the overhead of establishing connections for each operation.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class SqlConnectionManager {

    private HikariDataSource dataSource;

    /**
     * Initializes a new SqlConnectionManager with the provided database configuration.
     * Sets up a connection pool using HikariCP with the configuration settings
     * specified in the provided DatabaseConfig object.
     *
     * @param config The database configuration object containing properties for setting up the connection pool.
     */
    public SqlConnectionManager(DatabaseConfig config) {
        // Initialize HikariCP configuration with database properties
        HikariConfig hikariConfig = new HikariConfig();

        // Set the JDBC URL, username, and password from the config
        hikariConfig.setJdbcUrl(config.getJdbcUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());

        // Optionally set additional HikariCP settings if necessary
        // Example: hikariConfig.setMaximumPoolSize(config.getMaxPoolSize());

        // Initialize the data source with the HikariCP configuration
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * Retrieves a database connection from the connection pool.
     * The connection is ready to be used for executing SQL statements.
     *
     * @return A {@link Connection} object representing the database connection.
     * @throws SQLException If a database access error occurs or the data source has been closed.
     */
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    /**
     * Closes the data source and releases all pooled connections.
     * This method should be called when the connection manager is no longer needed,
     * typically at the application shutdown, to ensure that all resources are properly released.
     */
    public void close() {
        if (this.dataSource != null && !this.dataSource.isClosed()) {
            this.dataSource.close();
        }
    }

    /**
     * Begins a new database transaction.
     *
     * @param conn The database connection.
     * @throws SQLException If an error occurs during setting auto-commit to false.
     */
    public void beginTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * Commits the current transaction.
     *
     * @param conn The database connection.
     * @throws SQLException If an error occurs during the transaction commit.
     */
    public void commitTransaction(Connection conn) throws SQLException {
        conn.commit();
    }

    /**
     * Rolls back the current transaction.
     *
     * @param conn The database connection.
     */
    public void rollbackTransaction(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            // Log the exception
        }
    }

    /**
     * Sets a savepoint in the current transaction.
     *
     * @param conn The database connection.
     * @param name The name of the savepoint.
     * @return A Savepoint object.
     * @throws SQLException If an error occurs during setting the savepoint.
     */
    public Savepoint setSavepoint(Connection conn, String name) throws SQLException {
        return conn.setSavepoint(name);
    }

    /**
     * Releases a previously set savepoint.
     *
     * @param conn      The database connection.
     * @param savepoint The savepoint to release.
     * @throws SQLException If an error occurs during releasing the savepoint.
     */
    public void releaseSavepoint(Connection conn, Savepoint savepoint) throws SQLException {
        conn.releaseSavepoint(savepoint);
    }

    /**
     * Resets the connection to its default auto-commit state.
     *
     * @param conn The database connection.
     * @throws SQLException If an error occurs during setting auto-commit to true.
     */
    public void resetConnection(Connection conn) throws SQLException {
        conn.setAutoCommit(true);
    }
}
