package tk.airshipcraft.commonlib.db;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Manages SQL database connections using HikariCP for pooling.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class SqlConnectionManager {

    private HikariDataSource dataSource;

    /**
     * Creates a connection pool manager for SQL databases.
     *
     * @param config The database configuration.
     */
    public SqlConnectionManager(DatabaseConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getProperty("jdbcUrl"));
        hikariConfig.setUsername(config.getProperty("username"));
        hikariConfig.setPassword(config.getProperty("password"));
        // Set additional HikariCP settings from config

        dataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * Retrieves a connection from the pool.
     *
     * @return A SQL database connection.
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
