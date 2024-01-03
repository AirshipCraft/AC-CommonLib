package tk.airshipcraft.commonlib.db;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.sql.Connection;
import java.sql.SQLException;

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

    // Additional methods for transaction management can be added as needed.
}